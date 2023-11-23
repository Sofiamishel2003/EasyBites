package com.example.easybites.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthCredential
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val loading = MutableLiveData(false)
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage
    fun setErrorMessage(message: String?) {
        _errorMessage.value = message
    }
    fun signInWithGoogleCredential(credential: AuthCredential, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("EasyBites", "Logueado con Google")
                        home()
                    }
                }
                .addOnFailureListener {
                    Log.d("EasyBites", "Fallo al loguear con Google")
                }
        } catch (ex: Exception) {
            Log.d("EasyBites", "Excepción al loguear con Google: ${ex.localizedMessage}")
        }
    }

    fun signOut(home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signOut()
            Log.d("EasyBites", "Cierre de sesión exitoso")
            home()
        } catch (ex: Exception) {
            Log.d("EasyBites", "Error al cerrar sesión: ${ex.localizedMessage}")
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split("@")?.get(0)
                        Log.d("EasyBites", "signInWithEmailAndPassword logueado")
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("EasyBites", "signInWithEmailAndPassword: ${task.result?.toString()}")
                    }
                }
        } catch (ex: Exception) {
            Log.d("EasyBites", "signInWithEmailAndPassword ${ex.message}")
        }
    }

    suspend fun isUsernameTaken(username: String): Boolean {
        return try {
            val querySnapshot = FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("username", username)
                .get()
                .await()

            !querySnapshot.isEmpty
        } catch (ex: Exception) {
            Log.e("EasyBites", "Error checking username availability: ${ex.localizedMessage}")
            false
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        username: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val isUsernameTaken = isUsernameTaken(username)

                if (isUsernameTaken) {
                    onComplete(false, "El nombre de usuario ya está en uso.")
                    return@launch
                }

                if (!loading.value!!) {
                    loading.value = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                createUserInFirestore(username)
                                onComplete(true, null)
                            } else {
                                onComplete(
                                    false,
                                    "Error al crear la cuenta: ${task.exception?.localizedMessage}"
                                )
                            }
                            loading.value = false
                        }
                }
            } catch (ex: Exception) {
                Log.e("EasyBites", "Error in createUserWithEmailAndPassword: ${ex.localizedMessage}")
                loading.value = false
            }
        }
    }


    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        user["user_id"] = userId.toString()
        user["display_name"] = displayName.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("EasyBites", "Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("EasyBites", "Error ${it}")
            }
    }

    private fun createUserInFirestore(username: String) {
        val userId = auth.currentUser?.uid
        val user = mutableMapOf<String, Any>()

        user["user_id"] = userId.toString()
        user["username"] = username

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("EasyBites", "Creado ${it.id}")
            }.addOnFailureListener {
                Log.e("EasyBites", "Error al crear usuario en Firestore: $it")
            }
    }
}

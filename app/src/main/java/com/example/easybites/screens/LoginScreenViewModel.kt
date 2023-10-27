package com.example.easybites.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val loading = MutableLiveData(false)

    fun signInWithEmailAndPassword(email:String, password:String,home: ()->Unit)
    = viewModelScope.launch{
        try{
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                        Log.d("EasyBites", "signInWithEmailAndPassword logueado")
                        createUser(displayName)
                        home()
                    }
                    else{
                        Log.d("EasyBites", "signInWithEmailAndPassword: ${task.result.toString()}")

                    }
                }

        }
        catch (ex:Exception){
            Log.d("EasyBites","signInWithEmailAndPassword ${ex.message}")
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
            }.addOnFailureListener{
                Log.d("EasyBites", "Error ${it}")

            }

    }

    fun createUserWithEmailAndPassword(
        email:String,
        password: String,
        home: ()-> Unit
    ){
        if ( loading.value== false){
            loading.value == true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        home()
                    }
                    else{
                        Log.d("EasyBites","CreateWithEmailAndPassword: ${task.result.toString()}")
                    }
                    loading.value == false
                }
        }
    }
}
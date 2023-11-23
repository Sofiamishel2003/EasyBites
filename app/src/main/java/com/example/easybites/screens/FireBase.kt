import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easybites.screens.Receta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MyViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var recetas = mutableStateListOf<Receta>()
        private set

    init {
        obtenerRecetas()
    }

    private fun obtenerRecetas() {
        val documentId = "8C6m2qCT2WtQ76XK3eD2"
        val documentId2 = "E4JNTtRZyC1bW6hu20G5"
        val documentoId3 = "Wj7gPiY6ZxFvQL9jorz0"
        val documentId4 = "egJaH73o2KtUNnTgKFdf"

        viewModelScope.launch {
            try {
                val documento = db.collection("recetas").document(documentId).get().await()

                if (documento.exists()) {
                    val imagenUrl: String = documento.getString("imagenUrl") ?: ""
                    val ingredientes: List<String> =
                        documento.get("ingredientes") as? List<String> ?: emptyList()
                    val presupuesto: String = documento.getString("presupuesto") ?: ""
                    val procedimiento: String = documento.getString("procedimiento") ?: ""
                    val tiempo: String = documento.getString("tiempo") ?: ""
                    val titulo: String = documento.getString("titulo") ?: ""

                    recetas.addAll(listOf(Receta(titulo, imagenUrl, ingredientes, tiempo, presupuesto, procedimiento)))

                    recetas.forEach { receta ->
                        Log.d("Datos doc: ", "${receta.titulo}, ${receta.imagenUrl}")
                    }
                } else {
                    Log.d("Error", "No existe el documento")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener los datos", e)
            }

            try {
                val documento = db.collection("recetas").document(documentId2).get().await()

                if (documento.exists()) {
                    val imagenUrl: String = documento.getString("imagenUrl") ?: ""
                    val ingredientes: List<String> =
                        documento.get("ingredientes") as? List<String> ?: emptyList()
                    val presupuesto: String = documento.getString("presupuesto") ?: ""
                    val procedimiento: String = documento.getString("procedimiento") ?: ""
                    val tiempo: String = documento.getString("tiempo") ?: ""
                    val titulo: String = documento.getString("titulo") ?: ""

                    recetas.addAll(listOf(Receta(titulo, imagenUrl, ingredientes, tiempo, presupuesto, procedimiento)))

                    recetas.forEach { receta ->
                        Log.d("Datos doc: ", "${receta.titulo}, ${receta.imagenUrl}")
                    }
                } else {
                    Log.d("Error", "No existe el documento")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener los datos", e)
            }
            try {
                val documento = db.collection("recetas").document(documentoId3).get().await()

                if (documento.exists()) {
                    val imagenUrl: String = documento.getString("imagenUrl") ?: ""
                    val ingredientes: List<String> =
                        documento.get("ingredientes") as? List<String> ?: emptyList()
                    val presupuesto: String = documento.getString("presupuesto") ?: ""
                    val procedimiento: String = documento.getString("procedimiento") ?: ""
                    val tiempo: String = documento.getString("tiempo") ?: ""
                    val titulo: String = documento.getString("titulo") ?: ""

                    recetas.addAll(listOf(Receta(titulo, imagenUrl, ingredientes, tiempo, presupuesto, procedimiento)))

                    recetas.forEach { receta ->
                        Log.d("Datos doc: ", "${receta.titulo}, ${receta.imagenUrl}")
                    }
                } else {
                    Log.d("Error", "No existe el documento")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener los datos", e)
            }
            try {
                val documento = db.collection("recetas").document(documentId4).get().await()

                if (documento.exists()) {
                    val imagenUrl: String = documento.getString("imagenUrl") ?: ""
                    val ingredientes: List<String> =
                        documento.get("ingredientes") as? List<String> ?: emptyList()
                    val presupuesto: String = documento.getString("presupuesto") ?: ""
                    val procedimiento: String = documento.getString("procedimiento") ?: ""
                    val tiempo: String = documento.getString("tiempo") ?: ""
                    val titulo: String = documento.getString("titulo") ?: ""

                    recetas.addAll(listOf(Receta(titulo, imagenUrl, ingredientes, tiempo, presupuesto, procedimiento)))

                    recetas.forEach { receta ->
                        Log.d("Datos doc: ", "${receta.titulo}, ${receta.imagenUrl}")
                    }
                } else {
                    Log.d("Error", "No existe el documento")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener los datos", e)
            }


        }
    }

}


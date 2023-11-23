import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easybites.screens.Receta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MyViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var recetas = mutableListOf<Receta>()

    init {
        obtenerRecetas()
    }

    private fun obtenerRecetas() {
        val documentId = "8C6m2qCT2WtQ76XK3eD2"
        val documentId2 = "E4JNTtRZyC1bW6hu20G5"

        viewModelScope.launch {
            try {
                val documento = db.collection("recetas").document(documentId).get().await()

                if (documento.exists()) {
                    val imagenUrl: String = documento.getString("imagenUrl") ?: ""
                    val ingredientes: List<String> = documento.get("ingredientes") as? List<String> ?: emptyList()
                    val presupuesto: String = documento.getString("presupuesto") ?: ""
                    val procedimiento: String = documento.getString("procedimiento") ?: ""
                    val tiempo: String = documento.getString("tiempo") ?: ""
                    val titulo: String = documento.getString("titulo") ?: ""

                    // Actualizar la lista mutable con la nueva Receta
                    recetas.clear()
                    recetas.add(Receta(titulo, imagenUrl, ingredientes, tiempo, presupuesto, procedimiento))

                    // Imprimir la lista de Recetas en el log
                    recetas.forEach { receta ->
                        Log.d("Datos doc: ", "${receta.titulo}, ${receta.imagenUrl}")
                    }
                } else {
                    Log.d("Error", "No existe el documento")
                }
            } catch (e: Exception) {
                // Manejar errores aquí
                Log.e("Error", "Error al obtener los datos", e)
            }

        }
    }
}

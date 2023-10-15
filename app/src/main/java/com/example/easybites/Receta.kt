package com.example.easybites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

@Composable
fun DetallesDeRecetaScreen(receta: Receta) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally  // Centra todo el contenido horizontalmente
    ) {
        // Título grande
        Text(
            text = receta.titulo,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Imagen
        val painter = rememberImagePainter(data = receta.imagenUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        // Listado de ingredientes
        Text(text = "Ingredientes:", style = MaterialTheme.typography.titleMedium)
        receta.ingredientes.forEach { ingrediente ->
            Row(
                horizontalArrangement = Arrangement.Center,  // Centra la fila
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "- $ingrediente",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),  // Aumentar el tamaño de la letra
                    textAlign = TextAlign.Right,  // Justificar a la derecha
                )
            }
        }
        // Espacio entre ingredientes y aproximados
        Spacer(modifier = Modifier.height(16.dp))
        // Tiempo y presupuesto
        Text(
            text = "Tiempo: ${receta.tiempo}",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
        )
        Text(
            text = "Presupuesto: ${receta.presupuesto}",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Título y procedimiento
        Text(text = "Procedimiento", style = MaterialTheme.typography.titleMedium)
        Text(
            text = receta.procedimiento,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetallesDeRecetaScreen() {
    val receta = Receta("Pizza", "https://example.com/image.jpg", listOf("Queso", "Tomate"), "30 min", "$20", "Hornea a 180 grados")
    DetallesDeRecetaScreen(receta)
}



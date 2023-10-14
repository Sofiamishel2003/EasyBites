package com.example.easybites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

@Composable
fun DetallesDeRecetaScreen(receta: Receta) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = receta.titulo,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
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
        Text(text = "Ingredientes:", style = MaterialTheme.typography.titleMedium)
        receta.ingredientes.forEach { ingrediente ->
            Text(text = "- $ingrediente", style = MaterialTheme.typography.bodyMedium)
        }
        Text(text = "Tiempo: ${receta.tiempo}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Presupuesto: ${receta.presupuesto}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Procedimiento: ${receta.procedimiento}", style = MaterialTheme.typography.bodyMedium)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDetallesDeRecetaScreen() {
    val receta = Receta("Pizza", "https://example.com/image.jpg", listOf("Queso", "Tomate"), "30 min", "$20", "Hornea a 180 grados")
    DetallesDeRecetaScreen(receta)
}

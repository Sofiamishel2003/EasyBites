import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.easybites.screens.Receta

@Composable
fun DetallesDeRecetaScreen(receta: Receta) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = receta.titulo,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = rememberImagePainter(data = receta.imagenUrl),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = "Ingredientes", style = MaterialTheme.typography.titleMedium)
        receta.ingredientes.forEach { ingrediente ->
            Text(
                text = "• $ingrediente",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tiempo: ${receta.tiempo}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Presupuesto: ${receta.presupuesto}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Procedimiento", style = MaterialTheme.typography.titleMedium)
        Text(
            text = receta.procedimiento,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}



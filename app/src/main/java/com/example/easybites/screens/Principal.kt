package com.example.easybites.screens
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import coil.compose.rememberImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

data class Receta(
    val titulo: String,
    val imagenUrl: String,
    val ingredientes: List<String>,
    val tiempo: String,
    val presupuesto: String,
    val procedimiento: String
)
class Principal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstructorP(recetas: List<Receta>)
{
        val navController = rememberNavController()
        NavHost(navController, startDestination = "lista_recetas") {
            composable("lista_recetas") {
                ListaDeRecetasScreen(navController, recetas)
            }
            composable("Receta/{index}",
                arguments = listOf(navArgument("index") {})
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                if (index != null && index < recetas.size) {
                    DetallesDeRecetaScreen(recetas[index])
                }
            }
        }

}
@ExperimentalMaterial3Api
@Composable
fun ListaDeRecetasScreen(navController: NavHostController, recetas: List<Receta>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp) // Centrar tarjetas
    ) {
        val chunkedRecetas = recetas.chunked(2) // Divide la lista en sublistas, cada una con 2 elementos como máximo
        itemsIndexed(chunkedRecetas) { index, recetaPair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for ((subIndex, receta) in recetaPair.withIndex()) {
                    RecetaCard(receta = receta) {
                        navController.navigate("Receta/${index * 2 + subIndex}")
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun RecetaCard(receta: Receta, onClick: () -> Unit) {
    val MyH6 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    val MyBody2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    // Usar Box en lugar de Card
    Box(
        Modifier
            .clickable(onClick = onClick)
            .width(180.dp)
            .padding(8.dp)
            .border(2.dp,Color.Black,shape= RoundedCornerShape(8.dp))
            .background(color= Color.Gray)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally  // Centrar texto horizontalmente

        ) {
            val painter = rememberImagePainter(data = receta.imagenUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = receta.titulo,
                style = MyH6,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = receta.tiempo,  // Asegurarse de mostrar el tiempo
                style = MyBody2,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

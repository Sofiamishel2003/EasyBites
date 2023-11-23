package com.example.busqueda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easybites.R
import com.example.easybites.screens.TopBarWithLogo


data class Receta(
    val nombre: String,
    val imageUrl: String,
    val ingredientes: List<String>,
    val tiempo: Int,
    val presupuesto: String,
    val procedimiento: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusquedaScreen() {

    val recetas = listOf(
        Receta("Pizza", "https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fill,g_center,w_730,h_913/k%2FPhoto%2FRecipe%20Ramp%20Up%2F2021-07-Almond-Flour-Pizza-Crust%2FALMOND-FLOUR-PIZZA-CRUST_KitchnKitchn3230-1-", listOf("Masa", "Tomate", "Queso"), 30 , "$20", "Hornea a 180 grados"),
        Receta("Sushi", "https://hips.hearstapps.com/hmg-prod/images/cropped-image-of-person-holding-sushi-at-table-royalty-free-image-1618595597.?crop=1.00xw:0.753xh;0,0.125xh&resize=1200:*", listOf("Arroz", "Pescado", "Algas"), 40 , "$25", "Enrollar y cortar"),
        Receta("Hamburguesa", "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg", listOf("Carne", "Pan", "Lechuga", "Tomate"), 15 , "$10", "Cocinar la carne y montar"),
        Receta("Tacos", "https://www.pequerecetas.com/wp-content/uploads/2020/10/tacos-mexicanos.jpg", listOf("Tortillas", "Carne", "Cilantro"), 20 , "$12", "Cocinar la carne y servir en tortillas"),
        Receta("Ensalada César", "https://www.gourmet.cl/wp-content/uploads/2016/09/Ensalada_C%C3%A9sar-web-553x458.jpg", listOf("Lechuga", "Pollo", "Queso Parmesano"), 10 , "$8", "Mezclar todo"),
        Receta("Spaghetti Carbonara", "https://www.twopeasandtheirpod.com/wp-content/uploads/2023/01/Spaghetti-Carbonara168766.jpg", listOf("Spaghetti", "Huevo", "Tocino"), 25 , "$15", "Cocinar la pasta y mezclar"),
        Receta("Pollo al Curry", "https://assets.unileversolutions.com/recipes-v2/34403.jpg", listOf("Pollo", "Curry", "Leche de Coco"), 40 , "$18", "Cocinar el pollo y añadir curry"),
        Receta("Gazpacho", "https://www.acouplecooks.com/wp-content/uploads/2021/07/Gazpacho-002s.jpg", listOf("Tomate", "Pimiento", "Pepino"), 15 , "$9", "Licuar y enfriar"),
        Receta("Steak", "https://whitneybond.com/wp-content/uploads/2021/06/steak-marinade-13.jpg", listOf("Carne", "Sal", "Pimienta"), 20 , "$22", "Asar a la parrilla"),
        Receta("Tiramisú", "https://cdn7.kiwilimon.com/recetaimagen/35448/640x640/42520.jpg.webp", listOf("Queso Mascarpone", "Café", "Bizcochos"), 45 , "$16", "Montar capas y enfriar")
    )

    var tiempo by remember { mutableStateOf("") }
    var recetasFiltradas by remember { mutableStateOf<List<Receta>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Buscar Recetas",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tiempo,
            onValueChange = { tiempo = it },
            label = { Text("Tiempo (minutos)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                recetasFiltradas = if (tiempo.isNotBlank()) {
                    val tiempoIngresado = tiempo.toIntOrNull() ?: 0
                    recetas.filter { it.tiempo <= tiempoIngresado }
                } else {
                    emptyList()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD84012),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar todas las recetas o las recetas filtradas en LazyColumn
        LazyColumn {
            items(if (recetasFiltradas.isNotEmpty()) recetasFiltradas else recetas) { receta ->
                RecetaItem(receta = receta)
                Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Gray)
            }
        }
    }
}

@Composable
fun RecetaItem(receta: Receta) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = receta.nombre)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tiempo: ${receta.tiempo} minutos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusquedaScreen()
}
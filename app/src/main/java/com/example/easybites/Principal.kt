package com.example.easybites
import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import coil.compose.rememberImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val recetas = listOf(
                Receta("Pizza", "https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fill,g_center,w_730,h_913/k%2FPhoto%2FRecipe%20Ramp%20Up%2F2021-07-Almond-Flour-Pizza-Crust%2FALMOND-FLOUR-PIZZA-CRUST_KitchnKitchn3230-1-", listOf("Masa", "Tomate", "Queso"), "30 min", "$20", "Hornea a 180 grados"),
                Receta("Sushi", "https://hips.hearstapps.com/hmg-prod/images/cropped-image-of-person-holding-sushi-at-table-royalty-free-image-1618595597.?crop=1.00xw:0.753xh;0,0.125xh&resize=1200:*", listOf("Arroz", "Pescado", "Algas"), "40 min", "$25", "Enrollar y cortar"),
                Receta("Hamburguesa", "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg", listOf("Carne", "Pan", "Lechuga", "Tomate"), "15 min", "$10", "Cocinar la carne y montar"),
                Receta("Tacos", "https://www.pequerecetas.com/wp-content/uploads/2020/10/tacos-mexicanos.jpg", listOf("Tortillas", "Carne", "Cilantro"), "20 min", "$12", "Cocinar la carne y servir en tortillas"),
                Receta("Ensalada César", "https://www.gourmet.cl/wp-content/uploads/2016/09/Ensalada_C%C3%A9sar-web-553x458.jpg", listOf("Lechuga", "Pollo", "Queso Parmesano"), "10 min", "$8", "Mezclar todo"),
                Receta("Spaghetti Carbonara", "https://www.twopeasandtheirpod.com/wp-content/uploads/2023/01/Spaghetti-Carbonara168766.jpg", listOf("Spaghetti", "Huevo", "Tocino"), "25 min", "$15", "Cocinar la pasta y mezclar"),
                Receta("Pollo al Curry", "https://assets.unileversolutions.com/recipes-v2/34403.jpg", listOf("Pollo", "Curry", "Leche de Coco"), "40 min", "$18", "Cocinar el pollo y añadir curry"),
                Receta("Gazpacho", "https://www.acouplecooks.com/wp-content/uploads/2021/07/Gazpacho-002s.jpg", listOf("Tomate", "Pimiento", "Pepino"), "15 min", "$9", "Licuar y enfriar"),
                Receta("Steak", "https://whitneybond.com/wp-content/uploads/2021/06/steak-marinade-13.jpg", listOf("Carne", "Sal", "Pimienta"), "20 min", "$22", "Asar a la parrilla"),
                Receta("Tiramisú", "https://cdn7.kiwilimon.com/recetaimagen/35448/640x640/42520.jpg.webp", listOf("Queso Mascarpone", "Café", "Bizcochos"), "45 min", "$16", "Montar capas y enfriar")
            )
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
    }
}
@ExperimentalMaterial3Api
@Composable
fun ListaDeRecetasScreen(navController: NavHostController, recetas: List<Receta>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp) // Centrar tarjetas
    ) {
        itemsIndexed(recetas) { index, receta ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RecetaCard(receta = receta) {
                    navController.navigate("Receta/$index")
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

    Card(onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp)
    ) {
        Column {
            val painter = rememberImagePainter(data = receta.imagenUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewListaDeRecetasScreen() {
    val navController = rememberNavController()
    val recetas = listOf(
        Receta("Pizza", "https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fill,g_center,w_730,h_913/k%2FPhoto%2FRecipe%20Ramp%20Up%2F2021-07-Almond-Flour-Pizza-Crust%2FALMOND-FLOUR-PIZZA-CRUST_KitchnKitchn3230-1-", listOf("Masa", "Tomate", "Queso"), "30 min", "$20", "Hornea a 180 grados"),
        Receta("Sushi", "https://hips.hearstapps.com/hmg-prod/images/cropped-image-of-person-holding-sushi-at-table-royalty-free-image-1618595597.?crop=1.00xw:0.753xh;0,0.125xh&resize=1200:*", listOf("Arroz", "Pescado", "Algas"), "40 min", "$25", "Enrollar y cortar"),
        Receta("Hamburguesa", "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg", listOf("Carne", "Pan", "Lechuga", "Tomate"), "15 min", "$10", "Cocinar la carne y montar"),
        Receta("Tacos", "https://www.pequerecetas.com/wp-content/uploads/2020/10/tacos-mexicanos.jpg", listOf("Tortillas", "Carne", "Cilantro"), "20 min", "$12", "Cocinar la carne y servir en tortillas"),
        Receta("Ensalada César", "https://www.gourmet.cl/wp-content/uploads/2016/09/Ensalada_C%C3%A9sar-web-553x458.jpg", listOf("Lechuga", "Pollo", "Queso Parmesano"), "10 min", "$8", "Mezclar todo"),
        Receta("Spaghetti Carbonara", "https://www.twopeasandtheirpod.com/wp-content/uploads/2023/01/Spaghetti-Carbonara168766.jpg", listOf("Spaghetti", "Huevo", "Tocino"), "25 min", "$15", "Cocinar la pasta y mezclar"),
        Receta("Pollo al Curry", "https://assets.unileversolutions.com/recipes-v2/34403.jpg", listOf("Pollo", "Curry", "Leche de Coco"), "40 min", "$18", "Cocinar el pollo y añadir curry"),
        Receta("Gazpacho", "https://www.acouplecooks.com/wp-content/uploads/2021/07/Gazpacho-002s.jpg", listOf("Tomate", "Pimiento", "Pepino"), "15 min", "$9", "Licuar y enfriar"),
        Receta("Steak", "https://whitneybond.com/wp-content/uploads/2021/06/steak-marinade-13.jpg", listOf("Carne", "Sal", "Pimienta"), "20 min", "$22", "Asar a la parrilla"),
        Receta("Tiramisú", "https://cdn7.kiwilimon.com/recetaimagen/35448/640x640/42520.jpg.webp", listOf("Queso Mascarpone", "Café", "Bizcochos"), "45 min", "$16", "Montar capas y enfriar")
    )

    ListaDeRecetasScreen(navController, recetas)
}
package com.example.easybites.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easybites.screens.ConstructorP
import com.example.easybites.screens.ListaDeRecetasScreen
import com.example.easybites.screens.Principal
import com.example.easybites.screens.Receta
import com.example.easybites.screens.loginScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){
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
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.loginScreen.ruta ){
        composable(route=AppScreens.loginScreen.ruta){
            loginScreen(navController)
        }
        composable(route=AppScreens.principalScreen.ruta){
            ConstructorP(recetas)
        }
    }
}

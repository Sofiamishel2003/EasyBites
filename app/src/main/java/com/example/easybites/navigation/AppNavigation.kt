package com.example.easybites.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.easybites.screens.BusquedaScreen
import com.example.easybites.screens.ConstructorP
import com.example.easybites.screens.ListaDeRecetasScreen
import com.example.easybites.screens.Principal
import com.example.easybites.screens.Receta
import com.example.easybites.screens.UsuarioScreen
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
    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route in listOf(AppScreens.PrincipalScreen.ruta, AppScreens.UsuarioScreen.ruta, AppScreens.BusquedaScreen.ruta)) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = AppScreens.LoginScreen.ruta, modifier = Modifier.padding(innerPadding)) {
            composable(route=AppScreens.LoginScreen.ruta){
                loginScreen(navController)
            }
            composable(route=AppScreens.PrincipalScreen.ruta){
                ConstructorP(recetas)
            }
            composable(route = AppScreens.UsuarioScreen.ruta) {
                UsuarioScreen()
            }
            composable(route = AppScreens.BusquedaScreen.ruta) {
                BusquedaScreen()
            }
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        AppScreens.PrincipalScreen,
        AppScreens.BusquedaScreen,
        AppScreens.UsuarioScreen
    )

    BottomNavigation(
        backgroundColor = Color(0xFFEA4B25),
        contentColor = Color.White
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(screen.icon, contentDescription = null, tint = if (currentRoute == screen.ruta) Color(0xFFF7B569) else Color.White)
                },
                label = {
                    Text(screen.title, style = MaterialTheme.typography.caption.copy(color = if (currentRoute == screen.ruta) Color(0xFFF7B569) else Color.White))
                },
                selected = currentRoute == screen.ruta,
                selectedContentColor = Color(0xFFF7B569),
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    if (navController.currentDestination?.route != screen.ruta) {
                        navController.navigate(screen.ruta)
                    }
                }
            )
        }
    }
}
@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    return navBackStackEntry?.destination?.route
}

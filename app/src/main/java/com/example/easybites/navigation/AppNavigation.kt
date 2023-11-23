package com.example.easybites.navigation

import MyViewModel
import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
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


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: MyViewModel) {
    // Obtener las recetas del ViewModel

    val recetas = remember {
        Log.d("AppDebug", "Recetas: ${viewModel.recetas}")
        viewModel.recetas
    }

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

package com.example.easybites.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val ruta: String, val title: String, val icon: ImageVector) {
    object LoginScreen : AppScreens("login_screen", "Login", Icons.Default.Login)
    object PrincipalScreen : AppScreens("principal_screen", "Principal", Icons.Default.Home)
    object UsuarioScreen : AppScreens("usuario_screen", "Usuario", Icons.Default.Person)
    object BusquedaScreen : AppScreens("busqueda_screen", "Buscar", Icons.Default.Search)
}

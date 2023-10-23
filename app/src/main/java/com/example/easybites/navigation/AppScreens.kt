package com.example.easybites.navigation

sealed class AppScreens(val ruta:String){
    object loginScreen:AppScreens("login_screen")
    object registerScreen:AppScreens("register_screen")
    object principalScreen:AppScreens("principal_screen")
    object recetaScreen:AppScreens("lista_recetas")
    object busquedaScreen:AppScreens("busqueda_screen")
}

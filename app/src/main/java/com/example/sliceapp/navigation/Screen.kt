package com.example.sliceapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Details : Screen("details/{itemName}") {
        fun createRoute(itemName: String) = "details/$itemName"
    }
    object Cart : Screen("cart")
    object Profile : Screen("profile")
}

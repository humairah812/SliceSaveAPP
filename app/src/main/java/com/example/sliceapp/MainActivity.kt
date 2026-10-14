package com.example.sliceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sliceapp.navigation.Screen
import com.example.sliceapp.ui.AuthViewModel
import com.example.sliceapp.ui.CartViewModel
import com.example.sliceapp.ui.screens.*

import com.example.sliceapp.ui.theme.SliceappTheme

class MainActivity : ComponentActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliceappTheme {
                AppNavigation(authViewModel, cartViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(authViewModel: AuthViewModel, cartViewModel: CartViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController, authViewModel)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController, cartViewModel)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("itemName") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName")
            ProductDetailScreen(navController = navController, itemName = itemName, cartViewModel = cartViewModel)
        }
        composable(Screen.Cart.route) {
            CartScreen(navController, cartViewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}

package com.undef.manosLocalesCernikGaribaldi.activities.SignUp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.undef.manosLocalesCernikGaribaldi.activities.Main.HomeScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Profile.ProfileScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Products.ProductDetailScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Products.ProductsScreen

@Composable

fun NavigationCenter(navController: NavHostController){
    //aca swapeamos entre los fragmentes de forgot password
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("products") { ProductsScreen(navController) }
        composable ("profile"){ ProfileScreen(navController) }

        composable("productDetail") { ProductDetailScreen(navController) }

    }
}
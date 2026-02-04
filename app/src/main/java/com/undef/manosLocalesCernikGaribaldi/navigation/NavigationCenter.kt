package com.undef.manosLocalesCernikGaribaldi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.undef.manosLocalesCernikGaribaldi.Emprendimientos.EmprendimientoDetailScreen
import com.undef.manosLocalesCernikGaribaldi.Main.HomeScreen
import com.undef.manosLocalesCernikGaribaldi.Products.ProductDetailScreen
import com.undef.manosLocalesCernikGaribaldi.Products.ProductsScreen
import com.undef.manosLocalesCernikGaribaldi.Profile.EditarPerfilScreen
import com.undef.manosLocalesCernikGaribaldi.Profile.EditarPreferenciasScreen
import com.undef.manosLocalesCernikGaribaldi.Profile.ProfileScreen

@Composable

fun NavigationCenter(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("products") { ProductsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("editarPreferencias"){ EditarPreferenciasScreen(navController) }
        composable("emprendimientoDetail") { EmprendimientoDetailScreen(navController) }
        composable("productDetail") { ProductDetailScreen(navController) }
        composable("editarPerfilScreen"){ EditarPerfilScreen(navController) }

    }
}

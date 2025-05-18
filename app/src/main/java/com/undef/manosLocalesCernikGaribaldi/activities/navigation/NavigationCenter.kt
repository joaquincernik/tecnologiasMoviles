package com.undef.manosLocalesCernikGaribaldi.activities.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.undef.manosLocalesCernikGaribaldi.activities.Emprendimientos.EmprendimientoDetailScreen
import com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword.ScreenStep2
import com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword.ScreenStep3
import com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword.ViewContainer
import com.undef.manosLocalesCernikGaribaldi.activities.Main.HomeScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Main.MainScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Products.ProductDetailScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Products.ProductsScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Profile.EditarPerfilScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Profile.EditarPreferenciasScreen
import com.undef.manosLocalesCernikGaribaldi.activities.Profile.ProfileScreen

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

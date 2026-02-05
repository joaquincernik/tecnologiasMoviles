package com.undef.manosLocalesCernikGaribaldi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.undef.manosLocalesCernikGaribaldi.ui.emprendimientos.EmprendimientoDetailScreen
import com.undef.manosLocalesCernikGaribaldi.ui.main.HomeScreen
import com.undef.manosLocalesCernikGaribaldi.ui.products.ProductDetailScreen
import com.undef.manosLocalesCernikGaribaldi.ui.products.ProductsScreen
import com.undef.manosLocalesCernikGaribaldi.ui.profile.EditarPerfilScreen
import com.undef.manosLocalesCernikGaribaldi.ui.profile.EditarPreferenciasScreen
import com.undef.manosLocalesCernikGaribaldi.ui.profile.ProfileScreen

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

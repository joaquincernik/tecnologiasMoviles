package com.undef.manosLocalesCernikGaribaldi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.undef.manosLocalesCernikGaribaldi.ui.categorias.CategoriaDetailScreen
import com.undef.manosLocalesCernikGaribaldi.ui.categorias.CategoriasScreen
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
        composable("editarPreferencias") { EditarPreferenciasScreen(navController) }
        composable("editarPerfilScreen") { EditarPerfilScreen(navController) }
        composable("categorias"){ CategoriasScreen(navController) }

        //detail de emprendimiento
        composable(
            route = "emprendimientoDetail/{emprendimientoId}",
            arguments = listOf(
                navArgument("emprendimientoId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val emprendimientoId = backStackEntry.arguments?.getInt("emprendimientoId") ?: 0

            EmprendimientoDetailScreen(
                id = emprendimientoId,
                navController = navController
            )
        }

        //detail de producto
        composable(
            route = "productDetail/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val productId = backStackEntry.arguments?.getInt("productId") ?: 0

            ProductDetailScreen(
                productId = productId,
                navController = navController
            )
        }

        //detail de categoria
        composable(
            route = "categoriaDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            CategoriaDetailScreen(categoriaId = id, navController = navController)
        }

    }
}

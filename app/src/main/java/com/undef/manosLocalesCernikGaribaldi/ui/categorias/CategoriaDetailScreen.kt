package com.undef.manosLocalesCernikGaribaldi.ui.categorias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardProducto
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardProductoEnCaategoria
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar

@Composable
fun CategoriaDetailScreen(
    categoriaId: Int,
    navController: NavHostController,
    viewModel: CategoriaDetailViewModel = hiltViewModel()
) {

    // Cargamos los datos al entrar
    LaunchedEffect(categoriaId) {
        viewModel.loadCategoria(categoriaId)
    }

    val state by viewModel.uiState.observeAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = { TopBar(navController) }
        ) { padding ->
        state?.let { detalle ->
            LazyColumn(modifier = Modifier
                .padding(padding)
                .background(Color.White)
                .fillMaxSize()) {
                item {
                    Text(
                        text = detalle.categoria.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(detalle.productos) { producto ->
                    // Reutilizamos tu componente de CardProducto
                    CardProductoEnCaategoria(
                        item = producto,
                        categoriaNombre = detalle.categoria.name,
                        navController = navController,
                    )
                }
            }
        } ?: run {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
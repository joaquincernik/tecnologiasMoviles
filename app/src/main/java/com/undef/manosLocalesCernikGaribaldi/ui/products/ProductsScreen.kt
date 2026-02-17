package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardProducto
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar

@Composable
fun ProductsScreen(navController: NavHostController, viewModel: ProductViewModel = viewModel()) {

    // Convertimos el LiveData del ViewModel en un Estado de Compose
    val listaProductos by viewModel.listaProductos.observeAsState(initial = emptyList())

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(1)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex, navController) }

    ) { innerPadding ->
        ContentProducts(Modifier.padding(innerPadding), navController, productos = listaProductos)
    }
}

@Composable
fun ContentProducts(
    modifier: Modifier,
    navController: NavHostController,
    productos: List<ProductoConEmprendimiento>
) {
    var textSearch by remember { mutableStateOf(TextFieldValue("")) }
    //ahora lo filtramos
    val listaProductosFiltrados = productos.filter {
        it.producto.name.contains(textSearch.text, ignoreCase = true)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        OutlinedTextField(
            value = textSearch,
            onValueChange = { textSearch = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            placeholder = { Text("Buscar producto") },
            //bordes
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),
                focusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),

                //  Estos eliminan el borde
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            content = {
                itemsIndexed(listaProductosFiltrados, itemContent = { index, item ->
                    CardProducto(
                        item,
                        navController
                    )//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
    }

}
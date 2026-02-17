package com.undef.manosLocalesCernikGaribaldi.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.undef.manosLocalesCernikGaribaldi.utils.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.utils.components.Categoria
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar


// para que entiendas lo que hice, fijate este video minuto: 8:40
//https://youtu.be/ytCWjSN0iTI

@Composable
@Preview
fun Prev() {
    HomeScreen(rememberNavController())
}

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {

    // Convertimos el LiveData del ViewModel en un Estado de Compose
    val listaEmprendimientos by viewModel.listaEmprendimientos.observeAsState(initial = emptyList())

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex, navController) }

    ) { innerPadding ->
        Content(
            Modifier.padding(innerPadding),
            navController,
            emprendimientos = listaEmprendimientos
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    emprendimientos: List<EmprendimientosEntity>
) {

    var searchText by remember { mutableStateOf("") }
    // FILTRADO DINÁMICO:
    // Cada vez que searchText o emprendimientos cambien, se genera una nueva lista
    val listaFiltrada = remember(searchText, emprendimientos) {
        if (searchText.isEmpty()) {
            emprendimientos
        } else {
            emprendimientos.filter {
                // Filtramos por nombre, ignorando mayúsculas y minúsculas
                it.name.contains(searchText, ignoreCase = true)
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        //buscador por nombre
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Buscar emprendimiento por nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
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
                itemsIndexed(listaFiltrada, itemContent = { index, item ->
                    CardEmprendimiento(
                        item,
                        navController
                    )//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
    }
}


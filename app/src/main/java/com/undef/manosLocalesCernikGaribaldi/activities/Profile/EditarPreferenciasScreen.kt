package com.undef.manosLocalesCernikGaribaldi.activities.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular

@Composable
fun EditarPreferenciasScreen(navController: NavHostController){
    var selectedIndex by remember {
        mutableIntStateOf(2)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex,navController) }

    ){ innerPadding->
        EditarPreferencias(Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun EditarPreferencias(modifier: Modifier, navController: NavHostController){
    val categorias = Categoria.entries.toList() //traigo la lista completa de preferencias

    val categoriasSeleccionadas = remember { //vamos a suponer que esta es la lista de categorias que el usuario tiene
        mutableStateListOf(
            Categoria.MATE,
            Categoria.DEPORTES,
            Categoria.GAMING
        )
    }

    val categoriasNoSeleccionadas = remember {
        mutableStateListOf(*categorias.filter { it !in categoriasSeleccionadas }.toTypedArray())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){

        Text("Editar preferencias", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Presiona para eliminar:", fontSize = 14.sp)
        PreferenciasActuales(categoriasSeleccionadas) { categoria ->
            categoriasSeleccionadas.remove(categoria)
            categoriasNoSeleccionadas.add(categoria)
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text("Agregar más categorías:", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Presiona para agregar:", fontSize = 14.sp)
            PreferenciasDisponibles(categoriasNoSeleccionadas) { categoria ->
                categoriasNoSeleccionadas.remove(categoria)
                categoriasSeleccionadas.add(categoria)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))


        ButtonGuardar{ //este lo traigo de editarperfilscreen. medio choto pero se podria poner en un archivo aparte para mas modularidad. es para zafar ahora
            //aca tenes que hacer toda la logica de guardado en usuario
            navController.navigate("profile")
        }
    }


}

@Composable
fun PreferenciasActuales(preferencias: List<Categoria>,  onRemove: (Categoria) -> Unit){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(preferencias) { categoria->
            Preferencebutton(
                text = categoria.toString(),
                onClick = { onRemove(categoria) }
            )
        }
    }
}

@Composable
fun Preferencebutton(text: String, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 40.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable{onClick()},
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 10.sp ,
            fontFamily = FontMontserratRegular

        )
    }
}

@Composable
fun PreferenciasDisponibles( disponibles: List<Categoria>, onAdd: (Categoria) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(disponibles) { categoria ->
                PreferenciasDisponiblesButton(
                    text = categoria.toString(),
                    onClick = { onAdd(categoria) }
                )
            }
        }
    }
}
@Composable
fun  PreferenciasDisponiblesButton (text: String, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .width(200.dp) // Más ancho
            .height(50.dp) // Más alto
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable{onClick()},
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 10.sp ,
            fontFamily = FontMontserratRegular

        )
    }
}
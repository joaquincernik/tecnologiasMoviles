package com.undef.manosLocalesCernikGaribaldi.activities.Main
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.undef.manosLocalesCernikGaribaldi.activities.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.CardEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar


// para que entiendas lo que hice, fijate este video minuto: 8:40
//https://youtu.be/ytCWjSN0iTI

@Composable
@Preview
fun Prev(){
    HomeScreen(rememberNavController())
}

@Composable
fun HomeScreen(navController: NavHostController) {

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex, navController) }

    ){ innerPadding->
        Content(Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun Content(modifier: Modifier = Modifier, navController: NavHostController){

    //aca voy a hacer una lista de categorias como para probar nomas. habria que configurar bien despues la clase Categiruas y Emprendimientos.
    //A EMPRENDIMIENTOS HAY QUE AGREGARLE LA UBICACION TMB.
    val listaCategorias: MutableList<Categoria> = mutableListOf(Categoria.MATE, Categoria.REGIONAL)

    //esta es la lista de emprendimientos que tendriamos que levantar desde la BD. ahora la hardcodeo para probar despues tenemos que ver como hacer eso
    val listaEmprendimientos: List<Emprendimientos> = listOf(
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias ),
        Emprendimientos("EcoMates", painterResource(id = R.drawable.imagenprueba), "esto es una descripcion" , listaCategorias )
    )


    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        // Buscador REVISAR ESTO NO SE QUE ONDA ME LO DIO EL CHAT ME TENGO QUE IR XD
        OutlinedTextField(
            value = "",
            onValueChange = { /*  */ },
            placeholder = { Text("Buscar emprendimiento...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            content ={
                itemsIndexed(listaEmprendimientos, itemContent = {index, item ->
                    CardEmprendimiento(item, navController)//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
}}


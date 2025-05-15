package com.undef.manosLocalesCernikGaribaldi.activities.Main
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.manosLocalesCernikGaribaldi.activities.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.CardEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import java.nio.file.WatchEvent


// para que entiendas lo que hice, fijate este video minuto: 8:40
//https://youtu.be/ytCWjSN0iTI

@Composable
@Preview(showBackground = true)
fun HomeScreen(){
    Scaffold(
        topBar = { TopBar(navController = rememberNavController()) },
      //  bottomBar = { BottomBar() } la idea seria agregarlo aca ahora, pero hay que pensarla mejor

    ){ innerPadding->
        Content(Modifier.padding(innerPadding))
    }
}

@Composable
fun Content(modifier: Modifier = Modifier){

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
                    CardEmprendimiento(item)//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
}}


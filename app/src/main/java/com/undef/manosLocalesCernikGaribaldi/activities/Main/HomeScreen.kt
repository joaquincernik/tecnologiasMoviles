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
import androidx.compose.material.Card
import androidx.compose.ui.unit.sp
import com.undef.manosLocalesCernikGaribaldi.activities.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import java.nio.file.WatchEvent


// para que entiendas lo que hice, fijate este video minuto: 8:40
//https://youtu.be/ytCWjSN0iTI

@Composable
fun HomeScreen(modifier: Modifier = Modifier){

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
        //imagen superior de manos locales
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)  // misma altura que la imagen base
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangulomanoslocales),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.manoslocalesrectangulo), //no elegi el mejor nombre para la imagen pero esto es "manos locales"
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)  // centrada en el Box
                    .size(200.dp)
            )
        }
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
                    Emprendimiento(item) //vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
    }

}

@Composable
fun Emprendimiento(item : Emprendimientos) {

    Card(
        modifier = Modifier
            .fillMaxWidth() //modificador que hace que un Composable ocupe el ancho máximo disponible dentro del contenedor padre.
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp), //para que tenga la forma redondeadita
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {

        Row (
            modifier = Modifier
                .padding(16.dp)
        ){
            Image(
                painter = item.imagen, //acordate que imagen es un painter
                contentDescription = item.nombre,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)//para que no quede pegado a los bordes
                    .clip(CircleShape),
                contentScale = ContentScale.Crop // esto es para que no se corte la imagen, sino queda mal
            )

            Spacer(modifier = Modifier.width(16.dp)) // ES PARA SEPARAR ELEMENTOS


            Column(

                modifier = Modifier.fillMaxWidth(),


            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.nombre,

                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontMontserratRegular,
                        modifier = Modifier.weight(1f)

                    )
                    Text(
                        text = item.categorias.joinToString(" - ") { it.name },
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.descripcion,
                    fontSize = 14.sp,
                    fontFamily = FontMontserratRegular
                )

            }
        }
    }
}





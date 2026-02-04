package com.undef.manosLocalesCernikGaribaldi.Emprendimientos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.Products.Product
import com.undef.manosLocalesCernikGaribaldi.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.components.CardProducto
import com.undef.manosLocalesCernikGaribaldi.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratRegular


@Composable
@Preview
fun TopBarPrev() {
    EmprendimientoDetailScreen(rememberNavController())
}

@Composable
fun EmprendimientoDetailScreen(navController: NavHostController) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    //momentaneo
    val listaCategorias: MutableList<Categoria> = mutableListOf(Categoria.MATE, Categoria.REGIONAL)
    val emprendimientoPrueba = Emprendimientos(
        "Entre amigos",
        painterResource(R.drawable.emprendimientologo),
        "Es reconocido por su durabilidad, resistencia y capacidad de conservar la temperatura gracias a su construcción de acero inoxidable con aislamiento al vacío.Los termos Stanley son populares entre aventureros, trabajadores de campo y personas que necesitan mantener sus bebidas a la temperatura ideal durante todo el día. ",
        listaCategorias
    )
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = {
            BottomBar(selectedIndex, navController)
        }
    ) { innerPadding ->
        ContentEmprendimiento(Modifier.padding(innerPadding), navController, emprendimientoPrueba)
    }
}



@Composable
fun ContentEmprendimiento(
    modifier: Modifier,
    navController: NavHostController,
    emprendimiento: Emprendimientos
) {

    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(
                color = colorResource(R.color.blanquito),
            )
    ) {
        //imagen de arirba
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            // Imagen que rellena la Box completamente
            Image(
                painter = painterResource(R.drawable.fondomates),
                contentDescription = "Imagen de producto",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // para que se recorte proporcionalmente
            )
            // Filtro oscuro encima
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.7f))
            )
        }

        Box(
            modifier = Modifier
                .offset(y = (-20.dp))
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)) // <-- CLIP necesario
                .background(
                    color = Color.White,
                )
        )
        {
            Column(
                modifier = Modifier.fillMaxWidth()

            ) {

                Column(modifier = Modifier.fillMaxWidth()
                    ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        //imagen de circulo
                        Image(
                            painter = emprendimiento.imagen, //acordate que imagen es un painter
                            contentDescription = emprendimiento.nombre,
                            modifier = Modifier
                                .size(115.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop // esto es para que no se corte la imagen, sino queda mal
                        )

                        Spacer(modifier = Modifier.width(12.dp)) // ES PARA SEPARAR ELEMENTOS

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = emprendimiento.nombre,
                                fontSize = 22.sp,
                                fontFamily = FontMontserratBold,
                            )
                            Text(
                                text = "Villa Carlos Paz",
                                fontSize = 14.sp,
                                fontFamily = FontMontserratRegular,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        //fin de el icono y eso
                    }
                    // Línea degradada (border bottom)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = 20.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.celeste_fuerte),
                                        colorResource(R.color.azul_fuerte)
                                    ) // azul a verde
                                )
                            )
                    )
                    // Título de productos
                    Text(
                        text = "Productos",
                        fontSize = 20.sp,
                        fontFamily = FontMontserratBold,
                        modifier = Modifier.padding(start = 22.dp, top = 24.dp, bottom = 12.dp)
                    )

                    // Contenido de productos
                    ContentProducts(navController = navController)
                }
            }

        }
    }
}


@Composable
fun ContentProducts(navController: NavHostController) {
    var textSearch by remember { mutableStateOf(TextFieldValue("")) }
    val listaProductos = Product.getProductList()

    //ahora lo filtramos
    val listaProductosFiltrados = listaProductos.filter {
        it.nombre.contains(textSearch.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
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
                .height(500.dp) ,
            content = {
                itemsIndexed(listaProductosFiltrados, itemContent = { index, item ->
                    CardProducto(
                        item,
                        navController,
                        esconderEmprendimiento = true
                    )//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })

    }
}
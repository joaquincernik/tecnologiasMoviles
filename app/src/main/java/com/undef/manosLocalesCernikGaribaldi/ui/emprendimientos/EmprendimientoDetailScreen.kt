package com.undef.manosLocalesCernikGaribaldi.ui.emprendimientos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.EmprendimientoConProductos
import com.undef.manosLocalesCernikGaribaldi.ui.products.GradientButtonDetail
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardProductoEnEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratRegular


@Composable
fun EmprendimientoDetailScreen(
    navController: NavHostController,
    viewModel: EmprendimientoDetailViewModel = EmprendimientoDetailViewModel(),
    id: Int
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    // Cargamos los datos al entrar
    LaunchedEffect(id) {
        viewModel.loadEmprendimiento(id)
    }

    val detalle by viewModel.emprendimiento.observeAsState()

    //momentaneo
//    val listaCategorias: MutableList<Categoria> = mutableListOf(Categoria.MATE, Categoria.REGIONAL)

    Scaffold(
        containerColor = Color.White,
        topBar = { TopBar(navController) },
        bottomBar = {
            BottomBar(selectedIndex, navController)
        }
    ) { innerPadding ->
        detalle?.let { emprendimiento ->
            ContentEmprendimiento(Modifier.padding(innerPadding), navController, emprendimiento)

        }
    }
}


@Composable
fun ContentEmprendimiento(
    modifier: Modifier,
    navController: NavHostController,
    emprendimiento: EmprendimientoConProductos
) {
    val context = androidx.compose.ui.platform.LocalContext.current


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
            SubcomposeAsyncImage(
                model = emprendimiento.emprendimiento.photoUrl, //acordate que imagen es un painter
                contentDescription = emprendimiento.emprendimiento.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop, // esto es para que no se corte la imagen, sino queda mal
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error al cargar")
                    }
                }
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

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        //imagen de circulo

                        SubcomposeAsyncImage(
                            model = emprendimiento.emprendimiento.photoUrl, //acordate que imagen es un painter
                            contentDescription = emprendimiento.emprendimiento.name,
                            modifier = Modifier
                                .size(115.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop, // esto es para que no se corte la imagen, sino queda mal
                            loading = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },
                            error = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Error al cargar")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.width(12.dp)) // ES PARA SEPARAR ELEMENTOS

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = emprendimiento.emprendimiento.name,
                                fontSize = 22.sp,
                                fontFamily = FontMontserratBold,
                            )
                            Text(
                                text = emprendimiento.emprendimiento.location,
                                fontSize = 14.sp,
                                fontFamily = FontMontserratRegular,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        //fin de el icono y eso
                    }
                    // Línea degradada (border bottom)
                    Spacer(modifier = Modifier.height(6.dp)) // ES PARA SEPARAR ELEMENTOS

                    GradientButtonDetail(
                        text = "WhatsApp ${emprendimiento.emprendimiento.name}",
                    ) {
                        // Asegúrate de que tu entidad EmprendimientosEntity tenga el campo 'phone' o 'telefono'
                        compartirWpp(
                            context = context,
                            telefono = "+543644356502", // Cambia por el nombre real de tu campo

                        )
                    }
                   /* Box(
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
*/
                    // Título de productos
                    Text(
                        text = "Productos",
                        fontSize = 20.sp,
                        fontFamily = FontMontserratBold,
                        modifier = Modifier.padding(start = 22.dp, top = 24.dp, bottom = 12.dp)
                    )

                    // Contenido de productos
                    ContentProducts(
                        navController = navController,
                        emprendimiento.productos,
                        emprendimiento.emprendimiento.name
                    )
                }
            }

        }
    }
}


@Composable
fun ContentProducts(
    navController: NavHostController,
    listaProductos: List<ProductosEntity>,
    name: String
) {
    var textSearch by remember { mutableStateOf(TextFieldValue("")) }

    //ahora lo filtramos
    val listaProductosFiltrados = listaProductos.filter {
        it.name.contains(textSearch.text, ignoreCase = true)
    }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
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
                .height(500.dp),
            content = {
                itemsIndexed(listaProductosFiltrados, itemContent = { index, item ->
                    CardProductoEnEmprendimiento(
                        item,
                        name,
                        navController
                    )//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"

                })
            })

    }
}
fun compartirWpp(context: Context, telefono: String) {
    // Limpiamos el teléfono (solo números)
    val numeroLimpio = telefono.replace(" ", "").replace("+", "").replace("-", "")
    val mensaje = "Hola! Vi tu producto en la app Manos Locales "

    // Creamos el URI para WhatsApp
    val uri = Uri.parse("https://wa.me/$telefono?text=${Uri.encode(mensaje)}")

    val intent = Intent(Intent.ACTION_VIEW, uri)

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
    }
}
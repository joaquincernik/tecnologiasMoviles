package com.undef.manosLocalesCernikGaribaldi.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratSemiBold

@Composable
fun CardProducto(
    item: ProductoConEmprendimiento,
    navController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() //modificador que hace que un Composable ocupe el ancho máximo disponible dentro del contenedor padre.
            .padding(horizontal = 16.dp, vertical = 16.dp),
        shape = RoundedCornerShape(16.dp), //para que tenga la forma redondeadita
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.gris_claro).copy(
                alpha = 0.1f
            ),
        )
        // elevation = 8.dp, lo comento a estos dos porque me tira un conflicto nomas
//backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 20.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Image(
                 painter = painterResource(id = item.imagen), //acordate que imagen es un painter
                 contentDescription = item.nombre,
                 modifier = Modifier
                     .size(115.dp)
                     .clip(RoundedCornerShape(10.dp)),
                 contentScale = ContentScale.Crop // esto es para que no se corte la imagen, sino queda mal
             )*/
            SubcomposeAsyncImage(
                model = item.producto.photoUrl, //acordate que imagen es un painter
                contentDescription = item.producto.name,
                modifier = Modifier
                    .size(115.dp)
                    .clip(RoundedCornerShape(10.dp)),
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

            Spacer(modifier = Modifier.width(16.dp)) // ES PARA SEPARAR ELEMENTOS


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 5.dp, top = 14.dp, bottom = 10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.producto.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        fontFamily = FontMontserratBold,
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.producto.description,
                    fontSize = 14.sp,
                    fontFamily = FontMontserratRegular
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.celeste_fuerte).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = item.emprendimiento.name,
                        fontSize = 11.sp,
                        fontFamily = FontMontserratSemiBold,
                        color = colorResource(id = R.color.azul_fuerte)
                    )
                }


                /*Spacer(modifier = Modifier.height(12.dp))
                GradientButtonProducto("Ver producto") {
                    //aca le digo lo que se va a ejecutar cuadno se clickee
                    navController.navigate("productDetail")
                }
                GradientButtonProducto {
                    navController.navigate("productDetail")
                }*/
                Spacer(modifier = Modifier.height(12.dp))

// PRECIO + BOTÓN EN UNA FILA

                val precioFormateado = "$ %,d".format(item.producto.price)

                Column {
                    Text(
                        text = "Precio",
                        fontSize = 11.sp,
                        fontFamily = FontMontserratRegular,
                        color = Color.Gray
                    )

                    Text(
                        text = precioFormateado,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontMontserratBold,
                        color = colorResource(id = R.color.azul_fuerte)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                GradientButtonProducto {
                    navController.navigate("productDetail/${item.producto.Id}")
                }
            }

        }

    }
}

@Composable
fun CardProductoEnEmprendimiento(
    item: ProductosEntity,
    emprendimientoNombre: String,
    navController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth() //modificador que hace que un Composable ocupe el ancho máximo disponible dentro del contenedor padre.
            .padding(horizontal = 16.dp, vertical = 16.dp),
        shape = RoundedCornerShape(16.dp), //para que tenga la forma redondeadita
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.gris_claro).copy(
                alpha = 0.1f
            ),
        )
        // elevation = 8.dp, lo comento a estos dos porque me tira un conflicto nomas
//backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 20.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Image(
                 painter = painterResource(id = item.imagen), //acordate que imagen es un painter
                 contentDescription = item.nombre,
                 modifier = Modifier
                     .size(115.dp)
                     .clip(RoundedCornerShape(10.dp)),
                 contentScale = ContentScale.Crop // esto es para que no se corte la imagen, sino queda mal
             )*/
            SubcomposeAsyncImage(
                model = item.photoUrl, //acordate que imagen es un painter
                contentDescription = item.name,
                modifier = Modifier
                    .size(115.dp)
                    .clip(RoundedCornerShape(10.dp)),
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

            Spacer(modifier = Modifier.width(16.dp)) // ES PARA SEPARAR ELEMENTOS


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 5.dp, top = 14.dp, bottom = 10.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        fontFamily = FontMontserratBold,
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    fontFamily = FontMontserratRegular
                )
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.celeste_fuerte).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = emprendimientoNombre,
                        fontSize = 11.sp,
                        fontFamily = FontMontserratSemiBold,
                        color = colorResource(id = R.color.azul_fuerte)
                    )
                }


                /*Spacer(modifier = Modifier.height(12.dp))
                GradientButtonProducto("Ver producto") {
                    //aca le digo lo que se va a ejecutar cuadno se clickee
                    navController.navigate("productDetail")
                }
                GradientButtonProducto {
                    navController.navigate("productDetail")
                }*/
                Spacer(modifier = Modifier.height(12.dp))

// PRECIO + BOTÓN EN UNA FILA

                val precioFormateado = "$ %,d".format(item.price)

                Column {
                    Text(
                        text = "Precio",
                        fontSize = 11.sp,
                        fontFamily = FontMontserratRegular,
                        color = Color.Gray
                    )

                    Text(
                        text = precioFormateado,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontMontserratBold,
                        color = colorResource(id = R.color.azul_fuerte)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                GradientButtonProducto {
                    navController.navigate("productDetail/${item.Id}")
                }
            }

        }

    }
}


@Composable
fun GradientButtonProducto(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(110.dp)
            .height(30.dp)
            .padding(end = 10.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.celeste_fuerte), // azul claro
                        colorResource(id = R.color.azul_fuerte),  // azul oscuro
                    )
                ),
                shape = RoundedCornerShape(25.dp) // pill shape
            )
            .clickable { onClick() }
    ) {
        Text(
            text = "Ver producto",
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}



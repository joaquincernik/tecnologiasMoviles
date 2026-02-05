package com.undef.manosLocalesCernikGaribaldi.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratSemiBold


@Composable
fun CardEmprendimiento(item: Emprendimientos, navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth() //modificador que hace que un Composable ocupe el ancho máximo disponible dentro del contenedor padre.
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp), //para que tenga la forma redondeadita
        // elevation = 8.dp, lo comento a estos dos porque me tira un conflicto nomas
//backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
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

                Spacer(modifier = Modifier.height(12.dp))

                GradientButtonEmprendimiento {
                    navController.navigate("emprendimientoDetail")
                }
            }
        }
    }
}


@Composable
fun GradientButtonEmprendimiento(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(150.dp)
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
            text = "Ver emprendimiento",
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}




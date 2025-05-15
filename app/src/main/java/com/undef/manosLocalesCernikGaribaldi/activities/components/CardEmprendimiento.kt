package com.undef.manosLocalesCernikGaribaldi.activities.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular

@Composable
fun CardEmprendimiento(item: Emprendimientos) {

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

            }
        }
    }
}






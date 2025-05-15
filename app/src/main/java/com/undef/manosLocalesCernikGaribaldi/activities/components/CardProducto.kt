package com.undef.manosLocalesCernikGaribaldi.activities.components

import android.R
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.manosLocalesCernikGaribaldi.activities.Products.Product
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular

@Composable
fun CardProducto(item: Product) {

    Card(
        modifier = Modifier
            .fillMaxWidth() //modificador que hace que un Composable ocupe el ancho máximo disponible dentro del contenedor padre.
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp), //para que tenga la forma redondeadita
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.gris_claro).copy(alpha = 0.1f),
        )
        // elevation = 8.dp, lo comento a estos dos porque me tira un conflicto nomas
//backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 20.dp, end = 5.dp)
        ) {
            Image(
                painter = painterResource(id = item.imagen), //acordate que imagen es un painter
                contentDescription = item.nombre,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop // esto es para que no se corte la imagen, sino queda mal
            )

            Spacer(modifier = Modifier.width(16.dp)) // ES PARA SEPARAR ELEMENTOS


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 5.dp, top = 10.dp)
                ,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        fontFamily = FontMontserratBold,
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.descripcion,
                    fontSize = 12.sp,
                    fontFamily = FontMontserratRegular
                )

            }
        }
    }
}




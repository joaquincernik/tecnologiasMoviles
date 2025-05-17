package com.undef.manosLocalesCernikGaribaldi.activities.Emprendimientos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.Products.Product
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.activities.components.Emprendimientos
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular


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
        bottomBar = { BottomBar(selectedIndex, navController) }
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
                color = Color.White,
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
                        .size(100.dp)
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

        }
    }

}
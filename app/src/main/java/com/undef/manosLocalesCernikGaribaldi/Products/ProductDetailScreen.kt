package com.undef.manosLocalesCernikGaribaldi.Products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratLight
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratSemiBold

@Composable
@Preview
fun TopBarPrev() {
    ProductDetailScreen(rememberNavController())
}


@Composable
fun ProductDetailScreen(navController: NavHostController) {

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    //momentaneo
    val productoPrueba = Product(
        99,
        "Materazzi",
        8000,
        "Es reconocido por su durabilidad, resistencia y capacidad de conservar la temperatura gracias a su construcción de acero inoxidable con aislamiento al vacío.Los termos Stanley son populares entre aventureros, trabajadores de campo y personas que necesitan mantener sus bebidas a la temperatura ideal durante todo el día. ",
        "Entre amigos",
        R.drawable.matestanley,
        false
    )
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(selectedIndex,navController) }
    ) { innerPadding ->
        ContentProduct(Modifier.padding(innerPadding), productoPrueba, navController)
    }
}

@Composable
fun ContentProduct(modifier: Modifier, product: Product, navController: NavHostController) {
    Column(
        modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.blanquito),
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(325.dp)
        ) {
            // Imagen que rellena la Box completamente
            Image(
                painter = painterResource(id = product.imagen),
                contentDescription = "Imagen de producto",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // para que se recorte proporcionalmente
            )
        }

        // Box blanca con borde redondeado arriba
        Box(
            modifier = Modifier
                .offset(y = (-20.dp))
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)) // <-- CLIP necesario
                .background(
                    color = Color.White,
                )
                .padding(start = 24.dp, end = 24.dp, top = 30.dp) // padding interior del contenido
        ) {
            Column( modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

            ) {
                Text(
                    text = "Mate ${product.nombre}",
                    fontFamily = FontMontserratBold,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "$ ${product.precio}",
                    fontFamily = FontMontserratLight,
                    fontSize = 20.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = product.descripcion,
                    fontFamily = FontMontserratRegular,
                    color = Color.DarkGray,
                    lineHeight = 24.sp // Aquí defines el line height
                )

                Spacer(modifier = Modifier.height(20.dp))

                GradientButtonDetail("Contactar proveedor") { }

                Spacer(modifier = Modifier.height(14.dp))

            }
        }
    }
}



@Composable
fun GradientButtonDetail(
    text: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 24.dp)
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
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}


/*
@Composable
fun GradientButtonProductoDetail(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .padding(end = 15.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.celeste_fuerte), // azul claro
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.azul_fuerte),  // azul oscuro
                    )
                ),
                shape = RoundedCornerShape(25.dp) // pill shape
            )
            .clickable { onClick() }
    ) {
        Text(
            text = "Contactar con Manos locales",
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}*/
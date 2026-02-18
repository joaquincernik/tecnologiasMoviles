package com.undef.manosLocalesCernikGaribaldi.ui.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratLight
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratSemiBold

//@Composable
//@Preview
/*fun TopBarPrev() {
    ProductDetailScreen(rememberNavController())
}

*/
@Composable
fun ProductDetailScreen(navController: NavHostController, productId: Int, viewModel: ProductDetailViewModel = viewModel() ) {
    //cuando cambie product id se ejecuta este bloque
    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    val producto = viewModel.producto.observeAsState()

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(selectedIndex,navController) }
    ) { innerPadding ->
        // Si el producto ya cargó, mostramos el contenido
        producto.value?.let { item ->
            ContentProduct(
                modifier = Modifier.padding(innerPadding),
                product = item,
                navController = navController
            )
        } ?: run {
            // Aquí podrías mostrar un Shimmer o Loading
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Cargando producto...")
            }
        }}
}
@Composable
fun ContentProduct(
    modifier: Modifier,
    product: ProductoConEmprendimiento,
    navController: NavHostController
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.blanquito)),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {

        // 🔹 Imagen
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(325.dp)
            ) {
                SubcomposeAsyncImage(
                    model = product.producto.photoUrl,
                    contentDescription = product.producto.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
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
            }
        }

        // 🔹 Contenido blanco
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 30.dp)
            ) {

                Text(
                    text = product.producto.name,
                    fontFamily = FontMontserratBold,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = "$ ${product.producto.price}",
                    fontFamily = FontMontserratLight,
                    fontSize = 20.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = product.producto.description,
                    fontFamily = FontMontserratRegular,
                    color = Color.DarkGray,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                GradientButtonDetail(
                    text = "Contactar ${product.emprendimiento.name}"
                ) { }

                Spacer(modifier = Modifier.height(16.dp))
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
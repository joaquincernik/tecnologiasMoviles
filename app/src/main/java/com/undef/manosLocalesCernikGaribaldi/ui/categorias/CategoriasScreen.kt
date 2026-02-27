package com.undef.manosLocalesCernikGaribaldi.ui.categorias

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.ui.products.ContentProducts
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.CardProducto
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratBold
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratSemiBold

@Composable
fun CategoriasScreen(navController: NavHostController, viewModel: CategoriasViewModel = hiltViewModel()){
    val listaCategorias by viewModel.categorias.observeAsState(initial = emptyList())

    //esto es para el bototm bar
    var selectedIndex by remember {
        mutableIntStateOf(1)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex, navController) }

    ) { innerPadding ->
        ContentCategorias(Modifier.padding(innerPadding), navController, categorias = listaCategorias)
    }
}



@Composable
fun ContentCategorias(
    modifier: Modifier,
    navController: NavHostController,
    categorias: List<CategoriasEntity>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            content = {
                itemsIndexed(categorias, itemContent = { index, item ->
                    CardCategoria(
                        item,
                        navController
                    )//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
    }
}


@Composable
fun CardCategoria(
    item: CategoriasEntity,
    navController: NavHostController
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.1f),
        )
    ) {
        Row(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 20.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            ) {

                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = FontMontserratBold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Badge decorativo
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.celeste_fuerte).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Categoría",
                        fontSize = 11.sp,
                        fontFamily = FontMontserratSemiBold,
                        color = colorResource(id = R.color.azul_fuerte)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                GradientButtonCategoria {
                    navController.navigate("categoriaDetail/${item.id}")
                }
            }
        }
    }
}

@Composable
fun GradientButtonCategoria(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(150.dp)
            .height(34.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.celeste_fuerte),
                        colorResource(id = R.color.azul_fuerte),
                    )
                ),
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = "Ver productos",
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}
package com.undef.manosLocalesCernikGaribaldi.activities.Products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.Main.Content
import com.undef.manosLocalesCernikGaribaldi.activities.components.CardEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.activities.components.CardProducto
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular

@Composable
@Preview
fun ProductsScreen(){
    Scaffold(
    topBar = { TopBar(navController = rememberNavController(), arrowInvisible = true) },
    //  bottomBar = { BottomBar() } la idea seria agregarlo aca ahora, pero hay que pensarla mejor

    ){ innerPadding->
        ContentProducts(Modifier.padding(innerPadding))
    }
}

@Composable
fun ContentProducts(modifier: Modifier){
    var textSearch by remember { mutableStateOf(TextFieldValue("")) }
    val listaProductos= Product.getProductList()
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        OutlinedTextField(
            value = textSearch,
            onValueChange = { textSearch = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp , end = 20.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,

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
                .weight(1f),
            content ={
                itemsIndexed(listaProductos, itemContent = {index, item ->
                    CardProducto(item)//vamos a mostrar por pantalla los emprendimientos en la funcion definida mas abajo "Emprendimiento"
                })
            })
    }

}
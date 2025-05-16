package com.undef.manosLocalesCernikGaribaldi.activities.Products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar

@Composable

fun TopBarPrev(){
    TopBar(rememberNavController())
}

@Composable

fun ProductDetailScreen(navController: NavHostController){
    val productoPrueba = Product(99,"Materazzi","mate muy facha facha facha mouy facha muy facha", "Entre amigos",R.drawable.termostanley,false)
    Scaffold(
        topBar = { TopBar(navController = navController) },
        //  bottomBar = { BottomBar() } la idea seria agregarlo aca ahora, pero hay que pensarla mejor
    ){ innerPadding->
        ContentProduct(Modifier.padding(innerPadding), productoPrueba)
    }
}

@Composable
fun ContentProduct(modifier: Modifier, product: Product){
    Column (
        modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = product.imagen),
            contentDescription = "Imagen de producto",
            modifier = Modifier.fillMaxSize()
        )
    }
}
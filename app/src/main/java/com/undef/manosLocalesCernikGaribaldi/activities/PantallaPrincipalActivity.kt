package com.undef.manosLocalesCernikGaribaldi.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.navigation.NavigationCenter


class PantallaPrincipalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            //esto va a manejar las vistas del main
           MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavigationCenter(navController = navController)
}



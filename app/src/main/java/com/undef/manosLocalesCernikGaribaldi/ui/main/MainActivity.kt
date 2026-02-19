package com.undef.manosLocalesCernikGaribaldi.ui.main

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.ui.navigation.NavigationCenter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PantallaPrincipalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }
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



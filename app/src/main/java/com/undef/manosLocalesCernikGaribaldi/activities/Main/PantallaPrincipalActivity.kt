package com.undef.manosLocalesCernikGaribaldi.activities.Main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class PantallaPrincipalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            //esto va a manejar las vistas del main
            MainScreen()
        }

    }
}






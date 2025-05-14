package com.undef.manosLocalesCernikGaribaldi.activities.components


import android.R
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

import androidx.navigation.NavHostController


@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.homelogo),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = false, // o true si estás en esa pantalla
            onClick = {
                navController.navigate("home")
            }
        )
    }
}



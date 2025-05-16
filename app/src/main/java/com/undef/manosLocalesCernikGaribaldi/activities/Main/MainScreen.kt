package com.undef.manosLocalesCernikGaribaldi.activities.Main


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.undef.manosLocalesCernikGaribaldi.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.activities.Products.ProductsScreen
import com.undef.manosLocalesCernikGaribaldi.activities.SignUp.navigation.NavigationCenter
import com.undef.manosLocalesCernikGaribaldi.activities.components.NavItem
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar

// Para que entiendas el funcionamiento de lo que hice mirate este videito
// https://youtu.be/O9csfKW3dZ4

@Composable
fun MainScreen(navController: NavHostController) {

/*
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { //para que este abajo
            BottomBar(
                navItems,
                selectedIndex,
                navController
            )
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex,
            navController
        ) //le paso como parametro la pantalla que esta seleccionada
}
*/

}

//esta funcion define que es lo que se va a mostrar en pantalla.
@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavHostController) {
    HomeScreen(navController)
  /*  when (selectedIndex) {
        0 -> HomeScreen()
        1 -> ProductsScreen(navController = rememberNavController()) // le tengo que pasar el nav controller cuando muetro las pantallas, para volver atras
        2 -> ProfileScreen()
    }*/
}


package com.undef.manosLocalesCernikGaribaldi.activities.Main


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

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


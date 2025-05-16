package com.undef.manosLocalesCernikGaribaldi.activities.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R

//lo saque a otro lado para que lo podamos reutilizar nomas, pero estaba god
@Composable
fun BottomBar(
    selectedIndex: Int,
    //onItemSelected: (Int) -> Unit  //esto es lo que devuelve, cuando se toca el boton va a devolver el numero de index que toco
    navController: NavHostController
) {
    //aca podes poner hasta 5
    val navItems = listOf(
        NavItem(
            "Home",
            painterResource(id = R.drawable.homelogo),
            painterResource(id = R.drawable.notselectedhome)
        ),
        NavItem(
            "Box",
            painterResource(id = R.drawable.boxlogo),
            painterResource(id = R.drawable.notselectedbox)
        ),
        NavItem(
            "User",
            painterResource(id = R.drawable.profilelogo),
            painterResource(id = R.drawable.notselectedprofile)
        )
    )



    NavigationBar { // es un objeto que provee compose
        navItems.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("products")
                        2 -> navController.navigate("profile")
                    }
                },
                icon = {
                    Icon(
                        painter = if (selectedIndex == index) navItem.icon else navItem.notSelectedIcon,//para cambiar los iconos
                        contentDescription = navItem.label,
                        modifier = Modifier.size(100.dp) //tamaño imagen
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,           // Quita el violeta ese horrible que le pone arriba cuando lo tocas
                    selectedIconColor = Color.Unspecified,        // Usa color original de la imagen
                )
            )
        }
    }
}
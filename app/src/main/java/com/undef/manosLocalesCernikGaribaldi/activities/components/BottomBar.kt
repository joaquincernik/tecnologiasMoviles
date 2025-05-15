package com.undef.manosLocalesCernikGaribaldi.activities.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//lo saque a otro lado para que lo podamos reutilizar nomas, pero estaba god
@Composable
fun BottomBar(
    navItems: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit  //esto es lo que devuelve, cuando se toca el boton va a devolver el numero de index que toco
) {

    NavigationBar { // es un objeto que provee compose
        navItems.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    onItemSelected(index)
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
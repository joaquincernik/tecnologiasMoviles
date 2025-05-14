package com.undef.manosLocalesCernikGaribaldi.activities.Main


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import com.undef.manosLocalesCernikGaribaldi.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.undef.manosLocalesCernikGaribaldi.activities.components.NavItem


@Composable
fun MainScreen(modifier: Modifier = Modifier){

    val navItems = listOf(
        NavItem("Home", painterResource(id = R.drawable.homelogo),painterResource(id = R.drawable.notselectedhome)),
        NavItem("Box", painterResource(id = R.drawable.boxlogo),painterResource(id = R.drawable.notselectedbox)),
        NavItem("User", painterResource(id = R.drawable.profilelogo),painterResource(id = R.drawable.notselectedprofile))
    )

    var selectedIndex by remember{
        mutableIntStateOf(0)
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = { //para que este abajo
            NavigationBar { // es un objeto que provee compose
                navItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                painter = if (selectedIndex == index) navItem.icon else navItem.notSelectedIcon,//para cambiar los iconos
                                contentDescription = navItem.label,
                                modifier = Modifier.size(100.dp) //tamaño imagen
                            )
                        },
//                        label = {
//                            Text(text = navItem.label) si queres a este ponerlo puede llegar a ser a mi no me gusta
//                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,           // Quita el violeta ese horrible que le pone arriba cuando lo tocas
                            selectedIconColor = Color.Unspecified,        // Usa color original de la imagen
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex) //le paso como parametro la pantalla que esta seleccionada


    }
}

//esta funcion define que es lo que se va a mostrar en pantalla.
@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int){
    when(selectedIndex){
        0->HomeScreen()
        1->BoxScreen()
        2->ProfileScreen()
    }


}
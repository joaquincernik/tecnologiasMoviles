package com.undef.manosLocalesCernikGaribaldi.activities.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratSemiBold
@Composable
fun EditarPerfilScreen(navController: NavHostController){
    var selectedIndex by remember {
        mutableIntStateOf(2)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex,navController) }

    ){ innerPadding->
        Edit(Modifier.padding(innerPadding), navController)
    }
}
@Composable
fun Edit(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Editar perfil", fontSize = 22.sp, fontWeight = FontWeight.Bold)
    // acordate que toda esta info viene desde la clase User
        var nombreUsuario by rememberSaveable { mutableStateOf("") }
        var provincia by rememberSaveable { mutableStateOf("") }
        var ciudad by rememberSaveable { mutableStateOf("") }
        var telefono by rememberSaveable { mutableStateOf("") }
        var mail by rememberSaveable { mutableStateOf("") }

        EditableInfoCard("Usuario", nombreUsuario, onValueChange = { nombreUsuario = it })
        EditableInfoCard("Provincia", provincia, onValueChange = { provincia = it })
        EditableInfoCard("Ciudad", ciudad, onValueChange = { ciudad = it })
        EditableInfoCard("Telefono", telefono, onValueChange = { telefono = it })
        EditableInfoCard("Mail", mail, onValueChange = { mail = it })


        ButtonGuardar{
            //aca tenes que hacer toda la logica de guardado en usuario
            navController.navigate("profile")
        }

    }
}

@Composable
fun EditableInfoCard(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Column {
            //esto quedo medio choto hay que ver de arreglarlo
            // estaria piola agregar como hice en el signup el autocompletado en provincia creo que se hace con ExposedDropdownMenuBox
            Spacer(modifier = Modifier.height(4.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                label = { Text(label) },
                )
        }
    }
}

@Composable
fun ButtonGuardar(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.celeste_fuerte),
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.azul_fuerte)
                    )
                ),
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = "Guardar",
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}
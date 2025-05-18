package com.undef.manosLocalesCernikGaribaldi.activities.Profile
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.activities.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.undef.manosLocalesCernikGaribaldi.activities.LogIn.LoginActivity
import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.activities.components.GradientButtonProducto
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratSemiBold


@Composable
fun ProfileScreen(navController: NavHostController){

    var selectedIndex by remember {
        mutableIntStateOf(2)
    }
    Scaffold(
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex,navController) }

    ){ innerPadding->
        Profile(Modifier.padding(innerPadding), navController)
    }
}
@Composable
fun Profile(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        InfoCard("Usuario", "nombredeusuario")
        InfoCard("Provincia", "Chaco")
        InfoCard("Ciudad", "Presidencia Roque Saenz Pena")
        InfoCard("Telefono", "3644656565")
        InfoCard("Mail", "joapatopro@gmail.com")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Preferencias", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        val categorias = Categoria.entries.toList() //aca hardcodeamos las categorias, despues habria que ver como levantar de la bd que categorias tiene cada usuario.
        Preferencias(categorias)

        Spacer(modifier = Modifier.height(16.dp))
        //spacer
        GradientButtonPerfil{
            navController.navigate("productDetail") // aca iria a pantalla de editar perfil
        }

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current

        ButtonCerrarSesion(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        )

    }
}

@Composable
fun InfoCard(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column {
            Text(
                text = label,
                fontSize = 15.sp
            )
            Text(
                text = value,
                fontSize = 15.sp
            )
        }
    }

}
@Composable
fun GradientButtonPerfil (onClick: () -> Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.celeste_fuerte), // azul claro
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.azul_fuerte),  // azul oscuro
                    )
                ),
                shape = RoundedCornerShape(25.dp) // pill shape
            )
            .clickable { onClick() }
    ) {
        Text(
            text = "Editar perfil",
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}

@Composable
fun ButtonCerrarSesion(onClick: () -> Unit) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.celeste_fuerte),
                        colorResource(id = com.undef.manosLocalesCernikGaribaldi.R.color.azul_fuerte)
                    )
                ),
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = "Cerrar sesión",
            fontSize = 15.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}

@Composable
fun Preferencias(preferencias: List<Categoria>){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(preferencias) { categoria->
            PreferenceCard(text = categoria.toString())
        }
    }
}

@Composable
fun PreferenceCard(text: String){
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 40.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 10.sp ,
            fontFamily = FontMontserratRegular

        )
    }
}



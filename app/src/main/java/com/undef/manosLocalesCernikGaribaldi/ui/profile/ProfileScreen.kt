package com.undef.manosLocalesCernikGaribaldi.ui.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.utils.components.BottomBar
import com.undef.manosLocalesCernikGaribaldi.utils.components.TopBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.ui.login.LoginActivity
import com.undef.manosLocalesCernikGaribaldi.utils.components.Categoria
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.utils.theme.FontMontserratSemiBold


@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current // Obtenemos el contexto de la Activity
    // Observamos el LiveData y lo convertimos a State de Compose
    val user by viewModel.user.observeAsState()

    var selectedIndex by remember {
        mutableIntStateOf(2)
    }
    Scaffold(
        containerColor = Color.White,
        topBar = { TopBar(navController = navController, arrowInvisible = true) },
        bottomBar = { BottomBar(selectedIndex, navController) }

    ) { innerPadding ->
        Profile(Modifier.padding(innerPadding), navController, user, onLogout = {
            viewModel.logout()

            //Navegar a LoginActivity cerrando la actual
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            // 3. Cerramos la Activity actual (PantallaPrincipalActivity)
            (context as? Activity)?.finish()
        })
    }
}

@Composable
fun Profile(
    modifier: Modifier,
    navController: NavHostController,
    user: UsuariosEntity?, // Recibimos los datos de Room
    onLogout: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)

    ) {
        // Usamos los datos reales. Si 'user' es null (mientras carga), ponemos placeholders
        InfoCard("Usuario", user?.username ?: "Cargando...")
        InfoCard("Provincia", user?.provincia ?: "...")
        InfoCard("Ciudad", user?.ciudad ?: "...")
        InfoCard("Telefono", user?.telefono ?: "...")
        InfoCard("Mail", user?.email ?: "...")

        /*  InfoCard("Usuario", "nombredeusuario")
          InfoCard("Provincia", "Chaco")
          InfoCard("Ciudad", "Presidencia Roque Saenz Pena")
          InfoCard("Telefono", "3644656565")
          InfoCard("Mail", "joapatopro@gmail.com")
  */
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Preferencias",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            EditarPreferenciasButton {
                navController.navigate("editarPreferencias")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        val categorias =
            Categoria.entries.toList() //aca hardcodeamos las categorias, despues habria que ver como levantar de la bd que categorias tiene cada usuario.
        Preferencias(categorias)

        Spacer(modifier = Modifier.height(16.dp))
        //spacer
        GradientButtonPerfil {
            navController.navigate("editarPerfilScreen")
        } // aca iria a pantalla de editar perfil


        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current

        ButtonCerrarSesion(
            onClick = onLogout
            /*{
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }*/
        )

    }
}

@Composable
fun EditarPreferenciasButton(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,

        modifier = Modifier
            .height(20.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = "Editar Preferencias",
            color = Color.Gray,
            fontSize = 15.sp,
            fontFamily = FontMontserratSemiBold
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
fun GradientButtonPerfil(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.celeste_fuerte), // azul claro
                        colorResource(id = R.color.azul_fuerte),  // azul oscuro
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
                        colorResource(id = R.color.celeste_fuerte),
                        colorResource(id = R.color.azul_fuerte)
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
fun Preferencias(preferencias: List<Categoria>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(preferencias) { categoria ->
            PreferenceCard(text = categoria.toString())
        }
    }
}

@Composable
fun PreferenceCard(text: String) {
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 40.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontFamily = FontMontserratRegular

        )
    }
}



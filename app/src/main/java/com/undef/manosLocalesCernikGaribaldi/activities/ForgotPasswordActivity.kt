package com.undef.manosLocalesCernikGaribaldi.activities


import android.app.Activity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.ManosLocalesTheme

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    //aca swapeamos entre los fragmentes de forgot password
    NavHost(navController, startDestination = "paso1") {
        composable("paso1") { ViewContainer(navController) }
        composable("paso2") { Paso2() }
    }
}

@Composable
fun ViewContainer(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(navController) }
    ) { innerPadding ->
        Content(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun Paso2() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("¡Llegaste a la pantalla 2!", fontSize = 20.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    //como hayq ue volver a otra avtivity se hace esto
    val context = LocalContext.current
    val activity = context as? Activity

    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logoletras),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = {  activity?.finish() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Volver",

                    )
            }
        },

        modifier = Modifier
            .padding(14.dp)
            .padding(top = 25.dp)
    )
}

@Composable
fun Content(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.password1),
            contentDescription = "Imagen de un celular",
            modifier = Modifier
                .size(170.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "No te preocupes,\n ingresa tu mail",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontMontserratSemiBold
            ),
            textAlign = TextAlign.Center,
            /*modifier = Modifier
                .padding(top = 30.dp)*/
        )
        Spacer(modifier = Modifier.height(24.dp))
        Input()
        Spacer(modifier = Modifier.height(24.dp))
        GradientButton("Enviar codigo") {
            navController.navigate("paso2")
        }
    }

}

@Composable
fun Input() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Ingresa tu mail", fontFamily = FontMontserratRegular) },

        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(15.dp),
        singleLine = true
    )
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp)
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
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontMontserratSemiBold
        )
    }
}


//import de la fuente

val FontMontserratSemiBold = FontFamily(
    Font(R.font.montserratsemibold) // usa el nombre exacto del archivo .ttf (sin extensión en el recurso)
)

val FontMontserratRegular = FontFamily(
    Font(R.font.montserratregular) // usa el nombre exacto del archivo .ttf (sin extensión en el recurso)
)
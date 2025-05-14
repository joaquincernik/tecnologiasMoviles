package com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword


import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R

//import de los componentes
import com.undef.manosLocalesCernikGaribaldi.activities.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.activities.ui.theme.FontMontserratSemiBold


class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //esto va a manejar las vistas del forgot password
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
        composable("paso2") { ScreenStep2(navController) }
        composable("paso3") { ScreenStep3(navController) }

    }
}

@Composable
fun ViewContainer(navController: NavHostController) {
    Scaffold(
        //el top bar lo traigo de mi carpeta de componentes
        topBar = { TopBar(navController, true) }
    ) { innerPadding ->
        Content(modifier = Modifier.padding(innerPadding), navController)
    }
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
            //aca traigo el por defecto de mis tipografias y le cambio el fontsize
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
        Spacer(modifier = Modifier.height(50.dp))
        GradientButton("Enviar codigo") {
            //aca le digo lo que se va a ejecutar cuadno se clickee
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


/*
val FontMontserratSemiBold = FontFamily(
    Font(R.font.montserratsemibold) // usa el nombre exacto del archivo .ttf (sin extensión en el recurso)
)

val FontMontserratRegular = FontFamily(
    Font(R.font.montserratregular) // usa el nombre exacto del archivo .ttf (sin extensión en el recurso)
)*/
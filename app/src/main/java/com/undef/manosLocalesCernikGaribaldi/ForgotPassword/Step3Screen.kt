package com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.GradientButton
import com.undef.manosLocalesCernikGaribaldi.activities.PantallaPrincipalActivity
import com.undef.manosLocalesCernikGaribaldi.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratSemiBold


@Composable
fun ScreenStep3(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(navController ,false) }
    ) { innerPadding ->
        ContentStep3(modifier = Modifier.padding(innerPadding), navController)
    }
}


@Composable
fun ContentStep3(modifier: Modifier, navController: NavHostController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp, top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.forgotpassword3),
            contentDescription = "Imagen de un celular",
            modifier = Modifier
                .size(170.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Establece una nueva password",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontMontserratSemiBold
            ),
            textAlign = TextAlign.Center,
            /*modifier = Modifier
                .padding(top = 30.dp)*/
        )
        Spacer(modifier = Modifier.height(35.dp))
        NewPasswordInput()
        RepeatNewPasswordInput()
        Spacer(modifier = Modifier.height(50.dp))

        val context = LocalContext.current
        GradientButton("Continuar") {
            //aca le digo lo que se va a ejecutar cuadno se clickee
              //navController.navigate("home")
            val intent = Intent(context, PantallaPrincipalActivity::class.java )
            context.startActivity(intent)
        }
    }
}

@Composable
fun NewPasswordInput() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Nueva password", fontFamily = FontMontserratRegular) },

        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp , end = 20.dp),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,

        //bordes
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),
            focusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),

            //  Estos eliminan el borde
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}


@Composable
fun RepeatNewPasswordInput() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Repeti tu nueva password", fontFamily = FontMontserratRegular) },

        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp , end = 20.dp, top = 10.dp),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),
            focusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),

            //  Estos eliminan el borde
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        )
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewScreenStep3() {
    val navController = rememberNavController()
    ScreenStep3(navController)
}
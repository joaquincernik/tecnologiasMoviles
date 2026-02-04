package com.undef.manosLocalesCernikGaribaldi.ForgotPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.GradientButton
import com.undef.manosLocalesCernikGaribaldi.components.TopBar
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratRegular
import com.undef.manosLocalesCernikGaribaldi.theme.FontMontserratSemiBold

//esto seria la screen del step 2
@Composable
fun ScreenStep2(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(navController, false) }
    ) { innerPadding ->
        ContentStep2(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun ContentStep2(modifier: Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp, top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.forgotpassword2),
            contentDescription = "Imagen de un celular",
            modifier = Modifier
                .size(170.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Ingresa el codigo que te enviamos",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontMontserratSemiBold

            ),
            textAlign = TextAlign.Center,
            /*modifier = Modifier
                .padding(top = 30.dp)*/
        )
        Spacer(modifier = Modifier.height(35.dp))
        OtpInput(
            otpLength = 4,
            onOtpComplete = { }
        )
        Spacer(modifier = Modifier.height(50.dp))
        GradientButton("Verificar") {
            //aca le digo lo que se va a ejecutar cuadno se clickee
            navController.navigate("paso3")
        }
    }
}


@Composable
fun OtpInput(
    otpLength: Int = 4,
    onOtpComplete: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(otpLength) { index ->
            //getORNUll muestra el texto en la posicion actual, si no hay nada muestra null
            //cada vez que cambia se llama a esto
            val char = otpValue.getOrNull(index)?.toString() ?: ""

            //cada campo independiente pero todos usan el otpvalue
            OutlinedTextField(
                value = char,
                onValueChange = { value ->
                    //solo 1 caracter y que sea un digito
                    if (value.length <= 1 && value.all { it.isDigit() }) {

                        //stringBuilder es como un constructor de strings
                        val newValue = StringBuilder(otpValue)
                            // also permite ejecutar acciones sobre esta nueva cadena creada
                            .also {
                                //si el usuario ya escribio algo en esa posicion se reemplaza
                                if (otpValue.length > index) {
                                    it.setCharAt(index, value.firstOrNull() ?: ' ')
                                } else {
                                    it.append(value)
                                }
                            }
                            .toString()
                            .replace(" ", "")
                            .take(otpLength)

                        otpValue = newValue

                        if (newValue.length == otpLength) {
                            onOtpComplete(newValue)
                        }
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontMontserratRegular
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),
                    focusedContainerColor = colorResource(id = R.color.gris_claro).copy(alpha = 0.3f),

                    //  Estos eliminan el borde
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )

            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewScreenStep2() {
    val navController = rememberNavController()
    ScreenStep2(navController)
}
*/
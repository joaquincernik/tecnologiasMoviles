package com.undef.manosLocalesCernikGaribaldi.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.undef.manosLocalesCernikGaribaldi.ui.theme.ManosLocalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManosLocalesTheme {
                FirstText(name = "Joaquin")
            }
        }

       /* enableEdgeToEdge()
        setContent {
            ManosLocalesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
    }
}

@Composable
fun FirstText(name: String){
    Text(
        text = "Hola $name"
    )
}
@Composable
@Preview
fun FirstTextPreview(){
    FirstText("Joaquin")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ManosLocalesTheme {
        Greeting("Android")
    }
}
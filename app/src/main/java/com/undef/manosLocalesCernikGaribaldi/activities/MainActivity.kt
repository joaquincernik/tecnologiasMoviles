package com.undef.manosLocalesCernikGaribaldi.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.databinding.ActivityMainBinding
import com.undef.manosLocalesCernikGaribaldi.ui.theme.ManosLocalesTheme

class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIrARegistro.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
//            ManosLocalesTheme {
//                FirstText(name = "Joaquin")
//            }
//        }
//
//       /* enableEdgeToEdge()
//        setContent {
//            ManosLocalesTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }*/


//@Composable
//fun FirstText(name: String){
//    Text(
//        text = "Hola $name"
//    )
//}
//@Composable
//@Preview
//fun FirstTextPreview(){
//    FirstText("Joaquin")
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ManosLocalesTheme {
//        Greeting("Android")
//    }
//}
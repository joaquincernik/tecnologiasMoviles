package com.undef.manosLocalesCernikGaribaldi.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    //todo esto es para lo de las provincias
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos ViewBinding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargamos las provincias desde los recursos
        val provincias = resources.getStringArray(R.array.provincias_array)

        // Creamos el adaptador para el AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, provincias)

        // Configuramos el AutoCompleteTextView
        binding.autoProvincia.setAdapter(adapter)
        binding.autoProvincia.threshold = 1 //autocompleta desde el primer caracter

    // todo esto es para la validacion del mail
        /** Esto es asi. TestWatcher es un litener que permite escuchar a un EditText y tiene
         * 3 funciones. before.. que se llama antes de que se modifique el texto
         *              after que se modifica despues (vamosa usar esta)
         *              on que se llama mientras el texto cambia
         *
         * s es de un tipo de dato especial llamado Editable y se le agrega el ? para marcar que puede ser null
         *
         * Patterns.EMAIL_ADDRESS.matcher(email).matches()
         * Patterns es una clase android que contiene expresiones regulares predefinidas y se usa para
         * mails, para telefonos, para url, etc.
         * EMAIL_ADDRESS está dentro de la clase patterns y es para mails
         * matcher(email) es un método que se llama en la expresión regular para compararla
         * con un texto dado, en este caso, el valor del email. Este método retorna un Matcher
         *matches() es el metodo que realiza la comparacion.
         *
         *
         * **/
        binding.inputMail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString() //obtemenos el texto del editText

                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // Email válido

                    //aca tendria que estar la funcion que haria el chekeo con la bd si existe registrado este mail o no
                    binding.statusMailCheck.visibility = View.VISIBLE //acordate que los ids son "@+id/status_mail_check"
                    binding.statusMailError.visibility = View.GONE
                    binding.textoErrorMail.visibility = View.GONE
                } else {
                    // Email inválido
                    binding.statusMailCheck.visibility = View.GONE
                    binding.statusMailError.visibility = View.VISIBLE
                    binding.textoErrorMail.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} //overraideo y {} eso hace que no hagan nada las funciones
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // checkeo de contraseña
        binding.inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()

                if(password.length >= 8 && password.any { "!@#$%^&*()_+{}[]|:;,.<>?".contains(it) }  && password.any { it.isDigit() } && password.any { it.isLetter() }){

                    //contraseña es mayor a 8 caracteres, tiene alguno de esos caracteres especiales, tiene algun numero y alguna letra
                    binding.statusPasswordCheck.visibility = View.VISIBLE
                    binding.statusPasswordError.visibility = View.GONE
                    binding.textoErrorPassword.visibility = View.GONE

                } else{
                    binding.statusPasswordCheck.visibility = View.GONE
                    binding.statusPasswordError.visibility = View.VISIBLE
                    binding.textoErrorPassword.visibility = View.VISIBLE
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // checkeo de usuario existencia en BD
        /** TODO **/

        //checkeo de provincias




        }

}

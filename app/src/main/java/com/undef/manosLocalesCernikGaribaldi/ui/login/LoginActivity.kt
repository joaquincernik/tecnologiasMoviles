package com.undef.manosLocalesCernikGaribaldi.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.text
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.ui.forgotPassword.ForgotPasswordActivity
import com.undef.manosLocalesCernikGaribaldi.ui.main.PantallaPrincipalActivity
import com.undef.manosLocalesCernikGaribaldi.ui.signUp.SignUpActivity
import com.undef.manosLocalesCernikGaribaldi.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    // Inyectamos el ViewModel
    private val viewModel: LoginViewModel by viewModels()
    //significa que es una propiedad que se inicializa autpmaticametne usando un delegado
    //crea y mantiene un view model
    //by --> No me encargó yo de crear el valor, delego esa responsabilidad a otro objeto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //chequeo de sesion
        if (viewModel.checkSession()) {
            startActivity(Intent(this, PantallaPrincipalActivity::class.java))
            finish() // Cerramos el login para que no pueda volver atrás
            return // Importante salir del onCreate
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEventListeners()
        setupObservers()
    }

    private fun setupEventListeners() {
        binding.goRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.buttonIniciarSesion.setOnClickListener {
            val user = binding.inputCorreo.text.toString()
            val pass = binding.inputPassword.text.toString()

            // Le pedimos al ViewModel que procese el login
            viewModel.login(user, pass)
        }
    }

    private fun setupObservers() {
        //ACTIVITY escucha lso cambios de estado, cada vez que cambie el view model ejecutar
        viewModel.uiState.observe(this) { state ->
            //1.manejo del error
            state.errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            // lo mismo que if (state.errorMessage != null) {
            //    Toast.makeText(this, state.errorMessage, Toast.LENGTH_SHORT).show()
            //}

            // 2. Manejar éxito
            if (state.isSuccess) {
                val intent = Intent(this, PantallaPrincipalActivity::class.java)
                startActivity(intent)
                finish() // Cerramos login para que no pueda volver atrás
            }
        }


    }

}
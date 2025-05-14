package com.undef.manosLocalesCernikGaribaldi.activities.LogIn

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.undef.manosLocalesCernikGaribaldi.activities.ForgotPassword.ForgotPasswordActivity
import com.undef.manosLocalesCernikGaribaldi.activities.Main.PantallaPrincipalActivity
import com.undef.manosLocalesCernikGaribaldi.activities.SignUp.SignUpActivity
import com.undef.manosLocalesCernikGaribaldi.databinding.ActivityLoginBinding

class LoginActivity : ComponentActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.buttonIniciarSesion.setOnClickListener {
            //aca hay que agregar algun chequeo obvio para ver si el usuario existe, que ingreso bien los campos etc.
            val intent = Intent(this, PantallaPrincipalActivity::class.java)
            startActivity(intent)
        }


        //binding = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(binding.root)
    }
}
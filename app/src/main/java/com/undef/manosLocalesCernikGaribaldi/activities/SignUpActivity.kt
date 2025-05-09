package com.undef.manosLocalesCernikGaribaldi.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.undef.manosLocalesCernikGaribaldi.databinding.ActivitySignUpBinding
import com.undef.manosLocalesCernikGaribaldi.R

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BienvenidaFragment())
            .addToBackStack(null)
            .commit()

    }


}

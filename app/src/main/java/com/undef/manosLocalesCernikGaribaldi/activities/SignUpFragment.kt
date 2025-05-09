package com.undef.manosLocalesCernikGaribaldi.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.databinding.FragmentSignUpBinding
import android.text.TextWatcher
import android.text.Editable
import android.util.Patterns
import android.widget.Toast


class SignUpFragment : Fragment() {

    private var mail_check = false
    private var password_check = false
    private var provincias_check = false
    private var usuario_check = false
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowbacksignup.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val provincias = resources.getStringArray(R.array.provincias_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, provincias)
        binding.autoProvincia.setAdapter(adapter)
        binding.autoProvincia.threshold = 1

        binding.inputMail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.statusMailCheck.visibility = View.VISIBLE
                    binding.statusMailError.visibility = View.GONE
                    binding.textoErrorMail.visibility = View.GONE
                    mail_check = true
                } else {
                    binding.statusMailCheck.visibility = View.GONE
                    binding.statusMailError.visibility = View.VISIBLE
                    binding.textoErrorMail.visibility = View.VISIBLE
                    mail_check = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (password.length >= 8 &&
                    password.any { "!@#$%^&*()_+{}[]|:;,.<>?".contains(it) } &&
                    password.any { it.isDigit() } &&
                    password.any { it.isLetter() }
                ) {
                    binding.statusPasswordCheck.visibility = View.VISIBLE
                    binding.statusPasswordError.visibility = View.GONE
                    binding.textoErrorPassword.visibility = View.GONE
                    password_check = true
                } else {
                    binding.statusPasswordCheck.visibility = View.GONE
                    binding.statusPasswordError.visibility = View.VISIBLE
                    binding.textoErrorPassword.visibility = View.VISIBLE
                    password_check = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.inputUsuario.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (input.isNotEmpty()) {
                    binding.statusUsuarioCheck.visibility = View.VISIBLE
                    binding.statusUsuarioError.visibility = View.GONE
                    binding.textoErrorUsuario.visibility = View.GONE
                    usuario_check = true
                } else {
                    binding.statusUsuarioCheck.visibility = View.GONE
                    binding.statusUsuarioError.visibility = View.VISIBLE
                    binding.textoErrorUsuario.visibility = View.VISIBLE
                    usuario_check = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.autoProvincia.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val inputProvincia = s.toString().trim().lowercase()
                val provincias = resources.getStringArray(R.array.provincias_array).map { it.lowercase() }
                if (provincias.contains(inputProvincia)) {
                    binding.statusProvinciaCheck.visibility = View.VISIBLE
                    binding.statusProvinciaError.visibility = View.GONE
                    binding.textoErrorProvincia.visibility = View.GONE
                    provincias_check = true
                } else {
                    binding.statusProvinciaCheck.visibility = View.GONE
                    binding.statusProvinciaError.visibility = View.VISIBLE
                    binding.textoErrorProvincia.visibility = View.VISIBLE
                    provincias_check = false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.buttonRegistrarme.setOnClickListener {
            val telefono = binding.inputTelefono.text.toString().trim()
            val esValido = Patterns.PHONE.matcher(telefono).matches() && telefono.length >= 8
            val ciudad = binding.inputCiudad.text.toString().trim().isNotEmpty()

            if (esValido && ciudad && provincias_check && usuario_check && mail_check && password_check) {
                Toast.makeText(requireContext(), "Usuario Creado Con Exito", Toast.LENGTH_SHORT).show()
                // Navegar al siguiente fragment
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, BienvenidoFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                Toast.makeText(requireContext(), "Revisar los Campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

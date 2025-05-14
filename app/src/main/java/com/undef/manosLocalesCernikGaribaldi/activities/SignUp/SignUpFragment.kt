package com.undef.manosLocalesCernikGaribaldi.activities.SignUp

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

        // Configuramos el AutoCompleteTextView
        val provincias = resources.getStringArray(R.array.provincias_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, provincias)
        binding.autoProvincia.setAdapter(adapter)
        binding.autoProvincia.threshold = 1 //auto completa desde el primero

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
                val email = s.toString()
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // Email válido

                    //aca tendria que estar la funcion que haria el chekeo con la bd si existe registrado este mail o no
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

        // checkeo de contraseña
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

        //esto solo verifica que el campo usuario no este vacio, habria que verificar que no se encuentre registrado
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

        //verifica que la provincia ingresada sea valida
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

            //funcion que verifica que los campos sean todos correctos
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

package com.undef.manosLocalesCernikGaribaldi.ui.signUp

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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels

class SignUpFragment : Fragment() {

    //private var mail_check = false
    //private var password_check = false
    // private var provincias_check = false
    // private var usuario_check = false
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowbacksignup.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Configuramos el AutoCompleteTextView
        val provincias = resources.getStringArray(R.array.provincias_array)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, provincias)
        binding.autoProvincia.setAdapter(adapter)
        binding.autoProvincia.threshold = 1 //auto completa desde el primero

        setupObservers()
        setupInputs()

        //checkeo de mail
        /*

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
                })*/

        // checkeo de contraseña
        /*  binding.inputPassword.addTextChangedListener(object : TextWatcher {
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
  */
        //esto solo verifica que el campo usuario no este vacio, habria que verificar que no se encuentre registrado
        /* binding.inputUsuario.addTextChangedListener(object : TextWatcher {
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
 */
        /*

                 binding.autoProvincia.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        val inputProvincia = s.toString().trim().lowercase()
                        val provincias =
                            resources.getStringArray(R.array.provincias_array).map { it.lowercase() }
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
        */
        /*
          binding.buttonRegistrarme.setOnClickListener {
              val telefono = binding.inputTelefono.text.toString().trim()
              val esValido = Patterns.PHONE.matcher(telefono).matches() && telefono.length >= 8
              val ciudad = binding.inputCiudad.text.toString().trim().isNotEmpty()

              //funcion que verifica que los campos sean todos correctos
              if (esValido && ciudad && provincias_check && usuario_check && mail_check && password_check) {
                  Toast.makeText(requireContext(), "Usuario Creado Con Exito", Toast.LENGTH_SHORT)
                      .show()
                  // Navegar al siguiente fragment
                  val transaction = requireActivity().supportFragmentManager.beginTransaction()
                  transaction.replace(R.id.fragment_container, BienvenidoFragment())
                  transaction.addToBackStack(null)
                  transaction.commit()
              } else {
                  Toast.makeText(requireContext(), "Revisar los Campos", Toast.LENGTH_SHORT).show()
              }
          }
      */


    }


    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            val username = binding.inputUsuario.text.toString()
            val pass = binding.inputPassword.text.toString()
            val mail = binding.inputMail.text.toString()
            val telefono = binding.inputTelefono.text.toString()
            val ciudad = binding.inputCiudad.text.toString()
            val provincia = binding.autoProvincia.text.toString()

            // Actualizamos visibilidad de checks de mail
            binding.statusMailCheck.visibility =
                if (state.isMailValid) View.VISIBLE else View.GONE

            if (mail.isNotEmpty()){
                binding.statusMailError.visibility =
                    if (state.isMailValid && !mail.isEmpty()) View.GONE else View.VISIBLE
                binding.textoErrorMail.visibility =
                    if (state.isMailValid ) View.GONE else View.VISIBLE

            }

            // check pass
            binding.statusPasswordCheck.visibility =
                if (state.isPasswordValid) View.VISIBLE else View.GONE
            if(pass.isNotEmpty()){
                binding.statusPasswordError.visibility =
                    if (state.isPasswordValid) View.GONE else View.VISIBLE
                binding.textoErrorPassword.visibility =
                    if (state.isPasswordValid) View.GONE else View.VISIBLE

            }

            // check username
            binding.statusUsuarioCheck.visibility =
                if (state.isUsernameValid) View.VISIBLE else View.GONE
            if(username.isNotEmpty()){

                binding.statusUsuarioError.visibility =
                    if (state.isUsernameValid) View.GONE else View.VISIBLE
                binding.textoErrorUsuario.visibility =
                    if (state.isUsernameValid) View.GONE else View.VISIBLE

            }

            // check provincia
            binding.statusProvinciaCheck.visibility =
                if (state.isProvinciaValid) View.VISIBLE else View.GONE
            if(provincia.isNotEmpty()){

                binding.statusProvinciaError.visibility =
                    if (state.isProvinciaValid) View.GONE else View.VISIBLE
                binding.textoErrorProvincia.visibility =
                    if (state.isProvinciaValid) View.GONE else View.VISIBLE

            }

            // El botón solo se habilita si el formulario es válido
            binding.buttonRegistrarme.isEnabled = state.isFormValid

            if (state.isSuccess) {
                viewModel.register(username, mail, pass, ciudad, provincia, telefono)

            }
        }

    }

    private fun setupInputs() {
        binding.inputMail.addTextChangedListener { text ->
            viewModel.onMailChanged(text.toString())
        }
        binding.inputUsuario.addTextChangedListener { text ->
            viewModel.onUsernameChanged(text.toString())
        }
        binding.inputPassword.addTextChangedListener { text ->
            viewModel.onPasswordChanged(text.toString())
        }
        binding.inputCiudad.addTextChangedListener { text ->
            viewModel.onCiudadChanged(text.toString())
        }
        binding.inputTelefono.addTextChangedListener { text ->
            viewModel.onTelefonoChanged(text.toString())
        }
        binding.autoProvincia.addTextChangedListener { text ->
            viewModel.onProvinciaChanged(
                text.toString(),
                resources.getStringArray(R.array.provincias_array).toList()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // CRUCIAL para evitar fugas de memoria y crashes
    }
}

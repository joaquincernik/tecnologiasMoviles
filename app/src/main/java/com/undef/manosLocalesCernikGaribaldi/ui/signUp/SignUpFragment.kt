package com.undef.manosLocalesCernikGaribaldi.ui.signUp

import android.content.Intent
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
import com.undef.manosLocalesCernikGaribaldi.ui.main.PantallaPrincipalActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

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
    }


    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->

            // MANEJO DEL LOADING
            if (state.isLoading) {

                binding.buttonRegistrarme.isEnabled = false
                binding.buttonRegistrarme.text = "Cargando..."
            } else {
                binding.buttonRegistrarme.isEnabled = state.isFormValid
                binding.buttonRegistrarme.text = "Registrarme"
            }
            val username = binding.inputUsuario.text.toString()
            val pass = binding.inputPassword.text.toString()
            val mail = binding.inputMail.text.toString()
            val telefono = binding.inputTelefono.text.toString()
            val ciudad = binding.inputCiudad.text.toString()
            val provincia = binding.autoProvincia.text.toString()

            // Actualizamos visibilidad de checks de mail
            binding.statusMailCheck.visibility =
                if (state.isMailValid) View.VISIBLE else View.GONE

            if (mail.isNotEmpty()) {
                binding.statusMailError.visibility =
                    if (state.isMailValid && !mail.isEmpty()) View.GONE else View.VISIBLE
                binding.textoErrorMail.visibility =
                    if (state.isMailValid) View.GONE else View.VISIBLE

            }

            // check pass
            binding.statusPasswordCheck.visibility =
                if (state.isPasswordValid) View.VISIBLE else View.GONE
            if (pass.isNotEmpty()) {
                binding.statusPasswordError.visibility =
                    if (state.isPasswordValid) View.GONE else View.VISIBLE
                binding.textoErrorPassword.visibility =
                    if (state.isPasswordValid) View.GONE else View.VISIBLE

            }

            // check username
            binding.statusUsuarioCheck.visibility =
                if (state.isUsernameValid) View.VISIBLE else View.GONE
            if (username.isNotEmpty()) {

                binding.statusUsuarioError.visibility =
                    if (state.isUsernameValid) View.GONE else View.VISIBLE
                binding.textoErrorUsuario.visibility =
                    if (state.isUsernameValid) View.GONE else View.VISIBLE

            }

            // check provincia
            binding.statusProvinciaCheck.visibility =
                if (state.isProvinciaValid) View.VISIBLE else View.GONE
            if (provincia.isNotEmpty()) {

                binding.statusProvinciaError.visibility =
                    if (state.isProvinciaValid) View.GONE else View.VISIBLE
                binding.textoErrorProvincia.visibility =
                    if (state.isProvinciaValid) View.GONE else View.VISIBLE

            }

            // El botón solo se habilita si el formulario es válido
            binding.buttonRegistrarme.isEnabled = state.isFormValid

            binding.buttonRegistrarme.setOnClickListener {
                viewModel.register(username, mail, pass, ciudad, provincia, telefono)

            }
            if (state.isSuccess) {
                // Usamos requireContext() para obtener el contexto del Fragment
                val intent = Intent(requireContext(), PantallaPrincipalActivity::class.java)

                startActivity(intent)

                // Cerramos la Activity que contiene al fragment (LoginActivity o AuthActivity)
                requireActivity().finish()
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

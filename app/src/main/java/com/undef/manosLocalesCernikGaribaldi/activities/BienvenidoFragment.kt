package com.undef.manosLocalesCernikGaribaldi.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.databinding.FragmentBienvenidoBinding
import android.content.Intent


class BienvenidoFragment : Fragment() {
    private lateinit var binding: FragmentBienvenidoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBienvenidoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowbacksignup.setOnClickListener {
            //voy al anterior de la pila de fragments
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonIrAPantallaPrincipal.setOnClickListener {
            //vamos a la siguiente activity que es la pantalla principal
            val intent = Intent(requireContext(), PantallaPrincipalActivity::class.java)
            startActivity(intent)

        }
    }


}

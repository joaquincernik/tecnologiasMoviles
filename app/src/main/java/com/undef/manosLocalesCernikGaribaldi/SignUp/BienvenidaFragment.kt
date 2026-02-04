package com.undef.manosLocalesCernikGaribaldi.SignUp
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.LoginActivity
import com.undef.manosLocalesCernikGaribaldi.databinding.FragmentBienvenidaBinding


class BienvenidaFragment : Fragment() {

    private lateinit var binding: FragmentBienvenidaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBienvenidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.arrowbacksignup.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonCompletarTusDatos.setOnClickListener {

            // Realiza la transacción para navegar a otro fragmento
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Reemplaza el fragmento actual por el nuevo fragmento
            transaction.replace(R.id.fragment_container, SignUpFragment())

            // Agrega la transacción a la pila de retroceso para que se pueda volver
            transaction.addToBackStack(null)

            // Realiza la transacción
            transaction.commit()

        }

    }


}
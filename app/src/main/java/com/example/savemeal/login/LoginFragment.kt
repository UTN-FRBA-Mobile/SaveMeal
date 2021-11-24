package com.example.savemeal.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.savemeal.databinding.FragmentLoginBinding
import com.example.savemeal.MainActivity
import com.example.savemeal.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult

import com.google.android.gms.tasks.OnSuccessListener




class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener{
            val username = binding.usernameTextInput.text.toString()
            val password = binding.passwordTextInput.text.toString()

            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity,"Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener{
                        if (it.isSuccessful){

                            it.result?.user?.getIdToken(false)
                                ?.addOnSuccessListener(OnSuccessListener<GetTokenResult> { result ->
                                    val isShop: Boolean = result.claims["shop"] as Boolean
                                        // Navigate to main activity show available meals fragment
                                        val intent = Intent(this.activity, MainActivity::class.java).apply{
                                            putExtra("email", it.result?.user?.email)
                                            putExtra("isShop", isShop)
                                        }
                                        startActivity(intent)
                                })
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        binding.buttonConsumerSignUp.setOnClickListener{
            val action = R.id.action_loginFragment_to_signUpConsumerFragment
            findNavController().navigate(action)
        }

        binding.buttonShopSignUp.setOnClickListener{
            val action = R.id.action_loginFragment_to_signUpShopFragment
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Error de autenticación. Intentelo nuevamente")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
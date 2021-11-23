package com.example.savemeal.login

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
                //navigate to main activity
                val intent = Intent(this.activity, MainActivity::class.java)
                startActivity(intent)
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
}
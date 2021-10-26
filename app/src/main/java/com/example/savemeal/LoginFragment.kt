package com.example.savemeal

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.savemeal.databinding.FragmentLoginBinding

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
                Toast.makeText(activity,"Ingrese usuario y contrasseña", Toast.LENGTH_SHORT).show()
            } else {
                val action = R.id.action_loginFragment_to_welcomeFragment
                findNavController().navigate(action)
            }
        }

        binding.buttonConsumerSignUp.setOnClickListener{
            // One way to do it
            val action = R.id.action_loginFragment_to_signUpConsumerFragment
            findNavController().navigate(action)
        }

        binding.buttonShopSignUp.setOnClickListener{
            // Other way to do the same thing
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpShopFragment())
        }

    }
}
package com.example.androidpracticeproject.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.androidpracticeproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener {
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }

        binding.forgotPassword.setOnClickListener {
            view.findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        binding.continueWithGoogle.setOnClickListener {
            Toast.makeText(context,"To be implemented",Toast.LENGTH_SHORT).show()
        }

        binding.loginButton.setOnClickListener {
            Toast.makeText(context,"To be implemented",Toast.LENGTH_SHORT).show()
        }

    }

}
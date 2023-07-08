package com.example.androidpracticeproject.fragments.authentication

import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidpracticeproject.Constant
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.FragmentSignupBinding
import com.example.androidpracticeproject.views.HomeActivity


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        navController = findNavController()
        setUpOnClickListeners()
        colorAppTitleLogo()
    }

    private fun setUpOnClickListeners() {
        binding.btnSignup.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val emailAddress = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
            val gender =
                binding.rgGender.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId).text

            if (fullName.isNotEmpty() && emailAddress.isNotEmpty() && password.isNotEmpty() && gender.isNotEmpty()) {
                val pref = requireContext().getSharedPreferences(
                    Constant.PREFERENCE,
                    Context.MODE_PRIVATE
                )
                pref.edit()
                    .putString(Constant.Keys.PREF_KEY_FULL_NAME, fullName)
                    .putString(Constant.Keys.PREF_KEY_EMAIL, emailAddress)
                    .putString(Constant.Keys.PREF_KEY_PASSWORD, password)
                    .putBoolean(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN, true)
                    .apply()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.signUp_success),
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }

        binding.btnLogin.setOnClickListener {
            navController.popBackStack()
        }

        binding.btnContinueWithGoogle.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.signup_with_google_clicked),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun colorAppTitleLogo() {
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            0f,
            binding.tvAppNameLogo.paint.textSize,
            intArrayOf(
                resources.getColor(R.color.redColor, null),
                resources.getColor(R.color.purpleColor, null)
            ),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        binding.tvAppNameLogo.paint.shader = textShader
    }

}
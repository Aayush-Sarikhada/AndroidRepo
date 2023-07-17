package com.example.androidpracticeproject.fragments.authentication

import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.R
import com.example.android.databinding.FragmentSignupBinding
import com.example.android.nftbazaar.Constant
import com.example.android.nftbazaar.views.HomeActivity

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var btnLogin: Button
    private lateinit var tvAppTitleLogo: TextView
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
        btnLogin = binding.loginButton
        navController = findNavController()
        btnLogin.setOnClickListener {
            navController.popBackStack()
        }

        binding.signupButton.setOnClickListener {
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

        tvAppTitleLogo = binding.appNameLogo
        colorAppTitleLogo()
    }

    private fun colorAppTitleLogo() {
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            0f,
            tvAppTitleLogo.paint.textSize,
            intArrayOf(
                resources.getColor(R.color.redColor, null),
                resources.getColor(R.color.purpleColor, null)
            ),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        tvAppTitleLogo.paint.shader = textShader
    }

}
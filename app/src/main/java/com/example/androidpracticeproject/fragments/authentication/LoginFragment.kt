package com.example.androidpracticeproject.fragments.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidpracticeproject.Constant
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.FragmentLoginBinding
import com.example.androidpracticeproject.views.HomeActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var strPassword: String
    private lateinit var strEmail: String
    private lateinit var navController: NavController
    private var rememberUser = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        colorAppTitleLogo()
        navController = findNavController()
        sharedPreferences =
            requireActivity().getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE)

        binding.btnSignup.setOnClickListener {
            navController
                .navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }

        binding.forgotPassword.setOnClickListener {
            navController
                .navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        binding.btnContinueWithGoogle.setOnClickListener {
            Toast.makeText(context, getString(R.string.continue_with_google), Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnLogin.setOnClickListener {
            strEmail = binding.tilEmail.editText?.text.toString()
            strPassword = binding.tilPassword.editText?.text.toString()
            if (validateFields() && validateUser()) {
                storeRememberStateInSP()
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }

        binding.rememberMeCheckbox.addOnCheckedStateChangedListener { checkBox, _ ->
            rememberUser = checkBox.isChecked
        }

    }

    private fun validateUser(): Boolean {
        if (sharedPreferences.contains(Constant.Keys.PREF_KEY_EMAIL)) {
            if (strEmail == sharedPreferences.getString(
                    Constant.Keys.PREF_KEY_EMAIL,
                    ""
                ) && strPassword == sharedPreferences.getString(Constant.Keys.PREF_KEY_PASSWORD, "")
            ) {
                return true
            }
        }
        return false
    }

    private fun validateFields(): Boolean {
        return (strEmail.trim().isNotEmpty() && strPassword.trim().isNotEmpty())
    }

    private fun storeRememberStateInSP() {
        sharedPreferences.edit()
            .putBoolean(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN, rememberUser)
            .apply()
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
package com.example.android.webservices.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityUserSignUpBinding
import com.example.android.webservices.models.AuthRequest
import com.example.android.webservices.utils.FieldValidator
import com.example.android.webservices.viewmodels.UserAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivityUserSignUpBinding
    private val authViewModel: UserAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_sign_up)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpOnClickListeners() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etFullName.text.toString()
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()

            if (!FieldValidator.isFullNameValid(name)) {
                Toast.makeText(this, getString(R.string.name_must_not_be_empty), Toast.LENGTH_SHORT)
                    .show()
            } else if (!FieldValidator.isEmailValid(email)) {
                Toast.makeText(
                    this,
                    getString(R.string.please_enter_valid_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!FieldValidator.isPasswordValid(password)) {
                Toast.makeText(this, getString(R.string.password_length_error), Toast.LENGTH_SHORT)
                    .show()
            } else {
                authViewModel.registerUser(AuthRequest(email, name, password.toInt()))
            }

        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setUpObservers() {
        authViewModel.registerResponse.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
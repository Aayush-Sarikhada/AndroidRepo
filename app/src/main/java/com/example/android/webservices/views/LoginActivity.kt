package com.example.android.webservices.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityUserLoginBinding
import com.example.android.webservices.Constants
import com.example.android.webservices.common.ResponseCode
import com.example.android.webservices.models.AuthRequest
import com.example.android.webservices.utils.FieldValidator
import com.example.android.webservices.viewmodels.UserAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityUserLoginBinding
    private val authViewModel: UserAuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_login)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpOnClickListeners() {
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (!FieldValidator.isEmailValid(email)) {
                Toast.makeText(
                    this,
                    getString(R.string.please_enter_valid_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!FieldValidator.isPasswordValid(password)) {
                Toast.makeText(this, getString(R.string.password_length_error), Toast.LENGTH_SHORT)
                    .show()
            } else {
                authViewModel.loginUser(AuthRequest(email, password = password.toInt()))
            }

        }
    }

    private fun setUpObservers() {
        authViewModel.loginResponse.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if (it.code == ResponseCode.SUCCESS.ordinal) {
                Log.d("MAIN", it.data?.token.toString())
                getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE).edit()
                    .putString(
                        Constants.Keys.BEARER_TOKEN, it.data?.token
                    ).apply()
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                )
                finish()
            }
        }
    }
}
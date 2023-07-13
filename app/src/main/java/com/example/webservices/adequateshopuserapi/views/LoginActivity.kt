package com.example.webservices.adequateshopuserapi.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivityLoginBinding
import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.base.BaseActivity
import com.example.webservices.adequateshopuserapi.common.ResponseCode
import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.viewmodels.UserAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: UserAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpOnClickListeners() {
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()
            authViewModel.loginUser(AuthRequest(email, password = password.toInt()))
        }
    }

    private fun setUpObservers() {
        authViewModel.loginResponse.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            if(it.code == ResponseCode.SUCCESS.ordinal) {
                Log.d("MAIN", it.data?.token.toString())
                getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE).edit().putString(Constants.Keys.BEARER_TOKEN, it.data?.token).apply()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}
package com.example.webservices.adequateshopuserapi.views.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivitySignUpBinding
import com.example.webservices.adequateshopuserapi.base.BaseActivity
import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.viewmodels.UserAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val authViewModel: UserAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpOnClickListeners() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etFullName.text.toString()
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
            authViewModel.registerUser(AuthRequest(email,name,password.toInt()))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setUpObservers() {
        authViewModel.registerResponse.observe(this) {
            Log.d("MAIN", it.data?.token.toString())
            Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()
        }
    }
}
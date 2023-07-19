package com.example.android.webservices.views

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityCreateUserBinding
import com.example.android.webservices.Constants
import com.example.android.webservices.models.NewUserInfo
import com.example.android.webservices.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUserActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateUserBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var bearerToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        getBearerToken()
        setContentView(binding.root)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun getBearerToken() {
        val token =
            getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE).getString(
                Constants.Keys.BEARER_TOKEN,
                ""
            ).toString()
        bearerToken = "Bearer $token"
    }

    private fun setUpObservers() {
        userViewModel.user.observe(this) {
            Toast.makeText(this, getString(R.string.user_created_successfully), Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    private fun setUpOnClickListeners() {
        binding.btnCreateUser.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val email = binding.etUserEmail.text.toString()
            val location = binding.etUserLocation.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && location.isNotEmpty()) {
                val newUser = NewUserInfo(email, location, name)
                userViewModel.createUser(newUser)
            }
        }
    }
}


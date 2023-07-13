package com.example.webservices.adequateshopuserapi.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivityUserDetailsBinding
import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.base.BaseActivity
import com.example.webservices.adequateshopuserapi.viewmodels.UserViewModel
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private val userViewModel: UserViewModel by viewModels()
    private var bearerToken = "Bearer "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        getBearerToken()
        getUser(intent.getIntExtra(Constants.Keys.USER_ID, -1))
        setUpProgressBar(true)
        setUpObservers()
    }

    private fun getBearerToken() {
        bearerToken += getSharedPreferences(
            Constants.Strings.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(
            Constants.Keys.BEARER_TOKEN,
            ""
        ).toString()
    }

    private fun setUpProgressBar(visible: Boolean) {
        binding.progressUserLoading.isVisible = visible
    }

    private fun setUpObservers() {
        userViewModel.user.observe(this) { userData ->
            setUpUserDetails(userData)
        }
    }

    private fun getUser(id: Int) {
        if (id != -1) {
            userViewModel.getUserWith(id, bearerToken)
        }
    }

    private fun makeViewsVisible() {
        with(binding) {
            imgSingleUser.isVisible = true
            tvSingleUserId.isVisible = true
            tvSingleUserCreated.isVisible = true
            tvSingleUserLocation.isVisible = true
            tvSingleUserName.isVisible = true
            tvSingleUserEmail.isVisible = true
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUserDetails(user: UserInfo) {
        setUpProgressBar(visible = false)
        with(binding) {
            Picasso.get().load(user.profilePicture).into(imgSingleUser)
            tvSingleUserId.text = "ID: ${user.id}"
            tvSingleUserName.text = "Name: ${user.name}"
            tvSingleUserCreated.text = "Created On: ${user.createdAt}"
            tvSingleUserLocation.text = "Location: ${user.location}"
            tvSingleUserEmail.text = "Email: ${user.email}"
        }
        makeViewsVisible()
    }

}
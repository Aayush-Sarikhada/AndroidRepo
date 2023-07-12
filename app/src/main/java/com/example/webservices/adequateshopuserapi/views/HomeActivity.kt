package com.example.webservices.adequateshopuserapi.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivityHomeBinding
import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.Constants.Keys.BEARER_TOKEN
import com.example.webservices.adequateshopuserapi.adapters.UserListRVAdapter
import com.example.webservices.adequateshopuserapi.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userListAdapter: UserListRVAdapter
    private lateinit var bearerToken: String
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setUpRecyclerView()
        setUpObservers()
        getBearerToken()
        getUsersList()
        userViewModel.getUserWith(7075, bearerToken)
    }

    private fun getUsersList() {
        Log.d("HOME_ACTIVITY", bearerToken)
        userViewModel.getUsersOn(userViewModel.currentPage, bearerToken)
    }

    private fun getBearerToken() {
        val token = getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE).getString(
            BEARER_TOKEN,
            ""
        ).toString()
        bearerToken = "Bearer $token"
    }

    private fun setUpRecyclerView() {
        userListAdapter = UserListRVAdapter(emptyList())
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = userListAdapter
    }

    private fun setUpObservers() {
        userViewModel.userList.observe(this) {
            userListAdapter.submitUserList(it)
        }
    }

}
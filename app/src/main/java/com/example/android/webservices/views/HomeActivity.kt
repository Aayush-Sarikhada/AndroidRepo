package com.example.android.webservices.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.databinding.ActivityUserHomeBinding
import com.example.android.webservices.Constants
import com.example.android.webservices.Constants.Keys.BEARER_TOKEN
import com.example.android.webservices.adapters.UserListRVAdapter
import com.example.android.webservices.models.userList.UserInfo
import com.example.android.webservices.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var userListAdapter: UserListRVAdapter
    private lateinit var bearerToken: String
    private var isLoading = false
    private var userList = mutableListOf(UserInfo("", "", -1, "", "", ""))
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_home)
        setUpOnClickListeners()
        setUpRecyclerView()
        setUpObservers()
        getBearerToken()
        getUsersList()
    }

    private fun setUpOnClickListeners() {
        binding.topAppBar.setOnMenuItemClickListener {
            startActivity(Intent(this, CreateUserActivity::class.java))
            return@setOnMenuItemClickListener true
        }
    }

    private fun getUsersList() {
        val nextPage = ++userViewModel.currentPage
        userViewModel.getUsersOn(nextPage)
    }

    private fun getBearerToken() {
        val token =
            getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE).getString(
                BEARER_TOKEN,
                ""
            ).toString()
        bearerToken = "Bearer $token"
    }

    private fun setUpRecyclerView() {
        userListAdapter = UserListRVAdapter(userList)
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = userListAdapter
        setUpScrollListener()
    }

    private fun setUpObservers() {
        userViewModel.userList.observe(this) {
            val placeHolderUser = userList.removeLast()
            userList.addAll(it)
            userList.add(placeHolderUser)
            userListAdapter.submitUserList(userList)
        }

        userViewModel.isLoading.observe(this) {
            isLoading = it
        }
    }

    private fun setUpScrollListener() {
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if ((linearLayoutManager != null) && (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (userList.size - 1))) {
                        getUsersList()
                        isLoading = true
                    }
                }
            }
        })
    }

}
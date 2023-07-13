package com.example.webservices.adequateshopuserapi.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.javapractice.R
import com.example.javapractice.databinding.RowLoadingBinding
import com.example.javapractice.databinding.RowUserDataBinding
import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.example.webservices.adequateshopuserapi.views.home.UserDetailActivity
import com.squareup.picasso.Picasso

class UserListRVAdapter(private var userList: List<UserInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    inner class ItemViewHolder(binding: RowUserDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgView = binding.imgUser
        var tvId = binding.tvUserId
        var tvName = binding.tvUserName
        var tvCreatedOn = binding.tvUserCreatedOn
        var tvEmail = binding.tvUserEmail

        init {
            binding.root.setOnLongClickListener {
                val userId = userList[adapterPosition].id
                with(binding.root.context) {
                    startActivity(Intent(this, UserDetailActivity::class.java)
                        .apply {
                            putExtra(Constants.Keys.USER_ID, userId)
                        })
                }
                return@setOnLongClickListener true
            }
        }
    }

    class LoadingViewHolder(binding: RowLoadingBinding): RecyclerView.ViewHolder(binding.root) {
        val progressUsers = binding.progressCircularUsers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_user_data, parent, false)
            val binding = RowUserDataBinding.bind(view)
            ItemViewHolder(binding)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loading, parent, false)
            val binding = RowLoadingBinding.bind(view)
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder) {
            populateItemRows(holder,position)
        } else if(holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    fun submitUserList(newList: List<UserInfo>) {

        Log.d("DEBUG IN RVADAPTER", newList.toString())
        val oldList = userList
        userList = newList
        Log.d("DEBUG IN RVADAPTER", userList.toString())
        notifyItemRangeInserted(oldList.size-1,newList.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if(userList[position].id == -1) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    private fun showLoadingView(holder: LoadingViewHolder, position: Int) {
        holder.progressUsers.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun populateItemRows(holder: ItemViewHolder, position: Int) {
        Picasso.get().load(userList[position].profilePicture).into(holder.imgView)
        holder.tvId.text = "ID: ${userList[position].id}"
        holder.tvEmail.text = "Email: ${userList[position].email}"
        holder.tvName.text = "Name: ${userList[position].name}"
        holder.tvCreatedOn.text = "Created on: ${userList[position].createdAt}"
    }

}
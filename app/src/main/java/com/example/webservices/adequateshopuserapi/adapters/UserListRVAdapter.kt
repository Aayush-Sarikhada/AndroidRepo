package com.example.webservices.adequateshopuserapi.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.javapractice.R
import com.example.javapractice.databinding.RowUserDataBinding
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.squareup.picasso.Picasso

class UserListRVAdapter(private var userList: List<UserInfo>) :
    RecyclerView.Adapter<UserListRVAdapter.ViewHolder>() {

    inner class ViewHolder(binding: RowUserDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgView = binding.imgUser
        var tvId = binding.tvUserId
        var tvFirstName = binding.tvUserFirstName
        var tvLastName = binding.tvUserLastName
        var tvEmail = binding.tvUserEmail

        init {
            binding.root.setOnClickListener {
                userList[adapterPosition].id
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user_data, parent, false)
        val binding = RowUserDataBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(userList[position].profilePicture).into(holder.imgView)
        holder.tvId.text = "ID: ${userList[position].id}"
        holder.tvEmail.text = "Email: ${userList[position].email}"
        holder.tvFirstName.text = "Name: ${userList[position].name}"
        holder.tvLastName.text = "Created on: ${userList[position].createdAt}"
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitUserList(list: List<UserInfo>) {
        userList = list
        notifyDataSetChanged()
    }

}
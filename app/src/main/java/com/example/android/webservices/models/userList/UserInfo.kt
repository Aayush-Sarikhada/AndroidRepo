package com.example.android.webservices.models.userList

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("createdat")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profilepicture")
    val profilePicture: String
)
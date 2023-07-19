package com.example.android.webservices.models

import com.google.gson.annotations.SerializedName

data class NewUserInfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String
)
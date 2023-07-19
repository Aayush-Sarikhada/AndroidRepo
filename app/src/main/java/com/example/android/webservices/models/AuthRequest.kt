package com.example.android.webservices.models

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("password")
    val password: Int
)
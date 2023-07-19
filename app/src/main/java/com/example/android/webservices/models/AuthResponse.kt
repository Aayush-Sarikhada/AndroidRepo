package com.example.android.webservices.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String
)
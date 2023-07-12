package com.example.webservices.adequateshopuserapi.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String
)
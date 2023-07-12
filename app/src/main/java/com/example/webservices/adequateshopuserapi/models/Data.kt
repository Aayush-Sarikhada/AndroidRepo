package com.example.webservices.adequateshopuserapi.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Email")
    val email: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Token")
    val token: String
)
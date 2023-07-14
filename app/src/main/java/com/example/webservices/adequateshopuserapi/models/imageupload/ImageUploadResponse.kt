package com.example.webservices.adequateshopuserapi.models.imageupload


import com.google.gson.annotations.SerializedName

data class ImageUploadResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)
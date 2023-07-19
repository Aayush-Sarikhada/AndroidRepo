package com.example.android.webservices.models.imageupload

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("delete_url")
    val deleteUrl: String,
    @SerializedName("display_url")
    val displayUrl: String,
    @SerializedName("expiration")
    val expiration: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("medium")
    val medium: Medium,
    @SerializedName("size")
    val size: String,
    @SerializedName("thumb")
    val thumb: Thumb,
    @SerializedName("time")
    val time: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("url_viewer")
    val urlViewer: String,
    @SerializedName("width")
    val width: String
)
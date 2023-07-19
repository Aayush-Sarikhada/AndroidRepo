package com.example.android.webservices.models.userList

import com.google.gson.annotations.SerializedName

data class UsersList(
    @SerializedName("data")
    val `data`: List<UserInfo>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("totalrecord")
    val totalRecord: Int
)
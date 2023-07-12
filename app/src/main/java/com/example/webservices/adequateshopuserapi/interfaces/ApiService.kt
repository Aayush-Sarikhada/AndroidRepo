package com.example.webservices.adequateshopuserapi.interfaces

import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.models.AuthResponse
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.example.webservices.adequateshopuserapi.models.userList.UsersList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/authaccount/registration")
    suspend fun registerUser(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/authaccount/login")
    suspend fun loginUser(@Body authRequest: AuthRequest): Response<AuthResponse>

    @GET("/api/users")
    suspend fun getUsersOn(@Query("page") page: Int, @Header("Authorization") authorization: String): Response<UsersList>

    @GET("/api/users/{id}")
    suspend fun getUserWith(@Path("id") id: Int, @Header("Authorization") authorization: String): Response<UserInfo>

}
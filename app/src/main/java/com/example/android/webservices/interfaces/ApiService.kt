package com.example.android.webservices.interfaces

import com.example.android.webservices.models.AuthRequest
import com.example.android.webservices.models.AuthResponse
import com.example.android.webservices.models.NewUserInfo
import com.example.android.webservices.models.userList.UserInfo
import com.example.android.webservices.models.userList.UsersList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/authaccount/registration")
    suspend fun registerUser(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("/api/authaccount/login")
    suspend fun loginUser(@Body authRequest: AuthRequest): Response<AuthResponse>

    @GET("/api/users")
    suspend fun getUsersOn(
        @Query("page") page: Int
    ): Response<UsersList>

    @GET("/api/users/{id}")
    suspend fun getUserWith(
        @Path("id") id: Int
    ): Response<UserInfo>

    @POST("/api/users")
    suspend fun createUser(@Body userInfo: NewUserInfo): Response<UserInfo>

}
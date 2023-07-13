package com.example.webservices.adequateshopuserapi.interfaces

import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ImgBBApiService {

    @POST("/api/authaccount/registration")
    suspend fun registerUser(@Body authRequest: AuthRequest): Response<AuthResponse>


}
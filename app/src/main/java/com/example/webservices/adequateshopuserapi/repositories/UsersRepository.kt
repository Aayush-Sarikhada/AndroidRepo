package com.example.webservices.adequateshopuserapi.repositories

import com.example.webservices.adequateshopuserapi.interfaces.ApiService
import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.models.AuthResponse
import com.example.webservices.adequateshopuserapi.models.NewUserInfo
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.example.webservices.adequateshopuserapi.models.userList.UsersList

class UsersRepository(private val apiService: ApiService) {

    suspend fun registerUser(authRequest: AuthRequest): AuthResponse? {
        return apiService.registerUser(authRequest).body()
    }

    suspend fun loginUser(authRequest: AuthRequest): AuthResponse? {
        return apiService.loginUser(authRequest).body()
    }

    suspend fun getUsers(page: Int): UsersList? {
        return apiService.getUsersOn(page).body()
    }

    suspend fun getUserWith(id: Int): UserInfo? {
        return apiService.getUserWith(id).body()
    }

    suspend fun createUser(newUser: NewUserInfo): UserInfo? {
        return apiService.createUser(newUser).body()
    }

}
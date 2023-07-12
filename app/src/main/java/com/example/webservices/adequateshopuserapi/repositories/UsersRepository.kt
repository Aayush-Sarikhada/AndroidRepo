package com.example.webservices.adequateshopuserapi.repositories

import com.example.webservices.adequateshopuserapi.interfaces.ApiService
import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.models.AuthResponse
import com.example.webservices.adequateshopuserapi.models.userList.UsersList

class UsersRepository(private val apiService: ApiService) {

    suspend fun registerUser(authRequest: AuthRequest): AuthResponse? {
        return apiService.registerUser(authRequest).body()
    }

    suspend fun loginUser(authRequest: AuthRequest): AuthResponse? {
        return apiService.loginUser(authRequest).body()
    }

    suspend fun getUsers(page: Int, authorizationToken: String): UsersList? {
        return apiService.getUsersOn(page,authorizationToken).body()
    }

//    suspend fun getUser(id: Int): SingleUser? {
//        return apiService.getUser(id).body()
//    }
//
//    suspend fun createEmployee(employee: Employee): EmployeeCreatedResponse? {
//        return userApiService.createEmployee(employee).body()
//    }

}
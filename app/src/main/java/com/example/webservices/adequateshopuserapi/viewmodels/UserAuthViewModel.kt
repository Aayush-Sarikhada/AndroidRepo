package com.example.webservices.adequateshopuserapi.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webservices.adequateshopuserapi.models.AuthRequest
import com.example.webservices.adequateshopuserapi.models.AuthResponse
import com.example.webservices.adequateshopuserapi.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAuthViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {
    private val bearerToken = ""

    private val _registerResponse = MutableLiveData<AuthResponse>()
    var registerResponse = _registerResponse

    private val _loginResponse = MutableLiveData<AuthResponse>()
    var loginResponse = _loginResponse

    fun registerUser(registerRequest: AuthRequest) {
        viewModelScope.launch {
            val registerResponse = usersRepository.registerUser(registerRequest)
            Log.d("USER_AUTH_VIEW_MODEL", registerResponse?.data.toString())
        }
    }

    fun loginUser(loginRequest: AuthRequest) {
        viewModelScope.launch {
            val loginResponse = usersRepository.loginUser(loginRequest)
            Log.d("USER_AUTH_VIEW_MODEL", loginResponse?.data.toString())
        }
    }

}
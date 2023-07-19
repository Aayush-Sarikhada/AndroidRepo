package com.example.android.webservices.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.webservices.models.AuthRequest
import com.example.android.webservices.models.AuthResponse
import com.example.android.webservices.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAuthViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {
    private var bearerToken = ""
    private val _registerResponse = MutableLiveData<AuthResponse>()
    var registerResponse = _registerResponse
    private val _loginResponse = MutableLiveData<AuthResponse>()
    var loginResponse = _loginResponse

    fun registerUser(registerRequest: AuthRequest) {
        viewModelScope.launch {
            val response = usersRepository.registerUser(registerRequest)
            response?.let {
                _registerResponse.postValue(it)
            }
        }
    }

    fun loginUser(loginRequest: AuthRequest) {
        viewModelScope.launch {
            val response = usersRepository.loginUser(loginRequest)
            response?.let {
                _loginResponse.postValue(it)
                bearerToken = it.data?.token.toString()
            }
        }
    }

}
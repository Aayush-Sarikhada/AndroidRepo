package com.example.webservices.adequateshopuserapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webservices.adequateshopuserapi.models.NewUserInfo
import com.example.webservices.adequateshopuserapi.models.userList.UserInfo
import com.example.webservices.adequateshopuserapi.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {
    var currentPage = 0
    private val _perPage = MutableLiveData<Int>()
    var perPage: LiveData<Int> = _perPage
    private val _userList = MutableLiveData<List<UserInfo>>()
    var userList: LiveData<List<UserInfo>> = _userList
    private val _user = MutableLiveData<UserInfo>()
    var user: LiveData<UserInfo> = _user
    private val _isLoading = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    fun getUsersOn(page: Int, authorization: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = usersRepository.getUsers(page, authorization)
            response?.let {
                _perPage.postValue(response.perPage)
                _userList.postValue(response.data)
                _isLoading.postValue(false)
            }
        }
    }

    fun getUserWith(id: Int, authorization: String) {
        viewModelScope.launch {
            val response = usersRepository.getUserWith(id, authorization)
            response?.let {
                _user.postValue(it)
            }
        }
    }

    fun createUser(newUser: NewUserInfo, authorization: String) {
        viewModelScope.launch {
            val response = usersRepository.createUser(newUser, authorization)
            response?.let {
                _user.postValue(it)
            }
        }
    }

}
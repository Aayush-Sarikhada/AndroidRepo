package com.example.android.webservices.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.webservices.models.NewUserInfo
import com.example.android.webservices.models.userList.UserInfo
import com.example.android.webservices.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {
    var currentPage = 0
    private val _userList = MutableLiveData<List<UserInfo>>()
    var userList: LiveData<List<UserInfo>> = _userList
    private val _user = MutableLiveData<UserInfo>()
    var user: LiveData<UserInfo> = _user
    private val _isLoading = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    fun getUsersOn(page: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = usersRepository.getUsers(page)
            response?.let {
                _userList.postValue(response.data)
                _isLoading.postValue(false)
            }
        }
    }

    fun getUserWith(id: Int) {
        viewModelScope.launch {
            val response = usersRepository.getUserWith(id)
            response?.let {
                _user.postValue(it)
            }
        }
    }

    fun createUser(newUser: NewUserInfo) {
        viewModelScope.launch {
            val response = usersRepository.createUser(newUser)
            response?.let {
                _user.postValue(it)
            }
        }
    }

}
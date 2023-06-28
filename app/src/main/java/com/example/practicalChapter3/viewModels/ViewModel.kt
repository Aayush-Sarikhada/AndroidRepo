package com.example.practicalChapter3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private val _listOfMessages = MutableLiveData(mutableListOf<String>())
    val listOfMessage: LiveData<MutableList<String>>
        get() {
            return _listOfMessages
        }

    fun addToList(message: String) {
        Log.d("VIEW_MODEL", message)
        _listOfMessages.value?.add(message)
        Log.d("VIEW_MODEL", _listOfMessages.value.toString())
    }


}
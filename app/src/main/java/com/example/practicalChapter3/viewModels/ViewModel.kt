package com.example.practicalChapter3.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _listOfMessages = MutableLiveData<MutableList<String>>(mutableListOf())
    val listOfMessage: LiveData<MutableList<String>>
        get() {
            return _listOfMessages
        }

    private var _someData = MutableLiveData<Int>(0)
    val someData: LiveData<Int>
        get() = _someData

    fun setSomeData(data: Int) {
        _someData.value = data
    }

    fun addToList(message: String) {
        Log.d("VIEW_MODEL", message)
        _listOfMessages.value?.add(message)
        Log.d("VIEW_MODEL", _listOfMessages.value.toString())
    }

}
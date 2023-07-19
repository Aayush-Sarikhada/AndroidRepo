package com.example.android.practical3.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _listOfMessages = MutableLiveData<MutableList<String>>(mutableListOf())
    val listOfMessage: LiveData<MutableList<String>>
        get() = _listOfMessages
    private val messageList = mutableListOf<String>()

    var previewMessage = MutableLiveData("")

    fun addToList(message: String) {
        messageList.add(message)
        _listOfMessages.value = messageList
    }

    fun clearList() {
        messageList.clear()
        _listOfMessages.value = messageList
    }
}
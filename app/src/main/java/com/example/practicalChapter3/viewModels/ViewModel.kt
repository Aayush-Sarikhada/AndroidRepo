package com.example.practicalChapter3.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _listOfMessages = MutableLiveData<MutableList<String>>(mutableListOf())
    val listOfMessage: LiveData<MutableList<String>>
        get() = _listOfMessages
    private val messageList = mutableListOf<String>()

    var _previewMessage = MutableLiveData("Preview string")
    val previewMessage: LiveData<String>
        get() = _previewMessage

    fun updateMessage(msg: String) {
        _previewMessage.postValue(msg)
    }

    fun addToList(message: String) {
        messageList.add(message)
        _listOfMessages.postValue(messageList)
    }

    fun clearList() {
        messageList.clear()
        _listOfMessages.postValue(messageList)
    }
}
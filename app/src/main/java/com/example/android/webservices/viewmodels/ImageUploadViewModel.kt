package com.example.android.webservices.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.webservices.models.imageupload.ProgressCallback
import com.example.android.webservices.models.imageupload.ProgressRequestBody
import com.example.android.webservices.repositories.ImgBBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val imgBBRepository: ImgBBRepository
) : ViewModel() {

    private val _imageViewUrl = MutableLiveData<String>()
    val imageViewUrl = _imageViewUrl

    private val _imageResponse = MutableLiveData<Boolean>()
    val imageResponse = _imageResponse

    private val _progress = MutableLiveData<Long>()
    val progress = _progress

    fun uploadImage(file: File) {
        val partTo =
            ProgressRequestBody(file, MediaType.parse("image/*"), object : ProgressCallback {
                override fun onProgress(progress: Long) {
                    _progress.postValue(progress)
                }

            })
        val part = MultipartBody.Part.createFormData("image", file.name, partTo)

        viewModelScope.launch {
            val result = imgBBRepository.uploadImage(part)
            _imageResponse.postValue(result.isSuccess)
            _imageViewUrl.postValue(result.getOrNull()?.data?.displayUrl)
        }
    }
}
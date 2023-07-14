package com.example.webservices.adequateshopuserapi.repositories

import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.base.BaseRepository
import com.example.webservices.adequateshopuserapi.interfaces.ImgBBApiService
import com.example.webservices.adequateshopuserapi.models.imageupload.ImageUploadResponse
import okhttp3.MultipartBody

class ImgBBRepository(private val imgBBApiService: ImgBBApiService): BaseRepository() {

    suspend fun uploadImage(image: MultipartBody.Part): Result<ImageUploadResponse> {
        return handleResponse(
            imgBBApiService.uploadImage(
                Constants.APIConstants.IMGBB_API_KEY,
                image
            )
        )
    }

}
package com.example.android.webservices.repositories

import com.example.android.webservices.Constants
import com.example.android.webservices.base.BaseRepository
import com.example.android.webservices.interfaces.ImgBBApiService
import com.example.android.webservices.models.imageupload.ImageUploadResponse
import okhttp3.MultipartBody

class ImgBBRepository(private val imgBBApiService: ImgBBApiService) : BaseRepository() {

    suspend fun uploadImage(image: MultipartBody.Part): Result<ImageUploadResponse> {
        return handleResponse(
            imgBBApiService.uploadImage(
                Constants.APIConstants.IMGBB_API_KEY,
                image
            )
        )
    }

}
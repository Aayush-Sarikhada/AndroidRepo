package com.example.android.webservices.interfaces

import com.example.android.webservices.models.imageupload.ImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImgBBApiService {

    @Multipart
    @POST("/1/upload")
    suspend fun uploadImage(
        @Query("key") key: String,
        @Part image: MultipartBody.Part
    ): Response<ImageUploadResponse>

}
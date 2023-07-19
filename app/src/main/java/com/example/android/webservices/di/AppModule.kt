package com.example.android.webservices.di

import com.example.android.webservices.Constants
import com.example.android.webservices.interfaces.ApiService
import com.example.android.webservices.repositories.ImgBBRepository
import com.example.android.webservices.repositories.UsersRepository
import com.example.android.webservices.interfaces.ImgBBApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideUserRetrofit(gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(Constants.APIConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    @Named("ImgBB")
    fun provideImgBBRepository(gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(Constants.APIConstants.IMGBB_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: ApiService): UsersRepository =
        UsersRepository(apiService)

    @Provides
    @Singleton
    fun provideImageApiService(@Named("ImgBB") retrofit: Retrofit): ImgBBApiService =
        retrofit.create(ImgBBApiService::class.java)

    @Provides
    @Singleton
    fun provideImageRepository(imgBBApiService: ImgBBApiService): ImgBBRepository =
        ImgBBRepository(imgBBApiService)

}
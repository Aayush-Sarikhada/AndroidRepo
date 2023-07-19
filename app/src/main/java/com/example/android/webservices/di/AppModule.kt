package com.example.android.webservices.di

import android.content.Context
import com.example.android.webservices.Constants
import com.example.android.webservices.interfaces.ApiService
import com.example.android.webservices.interfaces.interceptors.ErrorInterceptor
import com.example.android.webservices.interfaces.interceptors.NetworkConnectionInterceptor
import com.example.android.webservices.interfaces.interceptors.UserAuthHeaderInterceptor
import com.example.android.webservices.repositories.UsersRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideUserRetrofit(@ApplicationContext context: Context, gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(UserAuthHeaderInterceptor(context))
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(ErrorInterceptor(context))
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.APIConstants.BASE_URL)
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

}
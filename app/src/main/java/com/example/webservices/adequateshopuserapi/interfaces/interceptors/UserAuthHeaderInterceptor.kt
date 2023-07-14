package com.example.webservices.adequateshopuserapi.interfaces.interceptors

import android.content.Context
import android.util.Log
import com.example.webservices.adequateshopuserapi.Constants
import com.example.webservices.adequateshopuserapi.common.Headers
import okhttp3.Interceptor
import okhttp3.Response

class UserAuthHeaderInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val token =
            context.getSharedPreferences(Constants.Strings.SHARED_PREF, Context.MODE_PRIVATE)
                .getString(Constants.Keys.BEARER_TOKEN, "")
        val bearerToken = "Bearer $token"
        if (!token.isNullOrEmpty()) {
            builder.addHeader(Headers.AUTHORIZATION.headerName, bearerToken)
        }
        return chain.proceed(builder.build())
    }
}

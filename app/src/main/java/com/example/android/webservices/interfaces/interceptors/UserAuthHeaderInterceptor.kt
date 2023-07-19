package com.example.android.webservices.interfaces.interceptors

import android.content.Context
import com.example.android.webservices.Constants
import com.example.android.webservices.common.Headers
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
        if (bearerToken.isNotEmpty()) {
            builder.addHeader(Headers.AUTHORIZATION.headerName, bearerToken)
        }
        return chain.proceed(builder.build())
    }
}

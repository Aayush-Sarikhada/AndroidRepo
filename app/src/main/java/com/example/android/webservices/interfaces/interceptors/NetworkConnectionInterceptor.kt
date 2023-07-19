package com.example.android.webservices.interfaces.interceptors

import android.content.Context
import com.example.android.webservices.common.extensions.NoConnectivityException
import com.example.android.webservices.utils.ConnectivityHelper
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) :
    Interceptor {

    override fun intercept(chain: Chain): Response {
        if (!ConnectivityHelper.isInternetConnected(context)) {
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

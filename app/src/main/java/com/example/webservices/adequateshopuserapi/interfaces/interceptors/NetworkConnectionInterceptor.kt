package com.example.webservices.adequateshopuserapi.interfaces.interceptors

import android.content.Context
import com.example.webservices.adequateshopuserapi.common.extensions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private val isConnected: Boolean
        get() {
            // TODO: add network checking here
            return false
        }
}

package com.example.webservices.adequateshopuserapi.common.extensions

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}
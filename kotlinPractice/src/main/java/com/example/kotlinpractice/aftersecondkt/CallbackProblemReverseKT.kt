package com.example.kotlinpractice.aftersecondkt

/*
Created By: Aayush Sarikhada
Updated on: 01 may 2023

This file contains callback problem solution given by prembhai in reverse KT.
*/

// problem given in second KT (related to callbacks)
enum class Response {
    AVAILABLE,
    NOT_AVAILABLE,
    NO_DATA
}

data class User(val firstName: String, val lastName: String) {
    val userName: String = "$firstName@$lastName"
}

class ApiService {
    fun getResponseWithDelay(userName: String, response: (Response) -> Unit) {
        if (userName == "Aayush@Sarikhada") response(Response.AVAILABLE) else response(Response.NOT_AVAILABLE)
    }
}

class Authenticator {
    inline fun authenticate(userName: String, crossinline onResponse: (Response) -> Unit) {
        ApiService().getResponseWithDelay(userName) {
            onResponse(it)
        }
    }
}

fun main() {
    var entryPermit = Response.NO_DATA
    val user = User("Aayush", "Sarikhada")
    Authenticator().authenticate(userName = user.userName) {response ->
        entryPermit = response
    }
    println(entryPermit) // prints: AVAILABLE
}
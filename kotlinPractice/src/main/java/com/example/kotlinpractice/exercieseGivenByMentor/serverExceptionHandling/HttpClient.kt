package com.example.kotlinpractice.exercieseGivenByMentor.serverExceptionHandling

import com.example.kotlinpractice.exercieseGivenByMentor.ServerExceptionCodes

class HttpClient(private val server: Server) {
    fun fetch(index: Int, onSuccess: (String) -> Unit, onFailure: (ServerExceptionCodes) -> Unit) = server.fetchData(index, onSuccess, onFailure)
}
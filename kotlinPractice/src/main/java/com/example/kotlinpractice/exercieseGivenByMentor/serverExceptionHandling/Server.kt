package com.example.kotlinpractice.exercieseGivenByMentor.serverExceptionHandling

import com.example.kotlinpractice.exercieseGivenByMentor.ServerExceptionCodes
import com.example.kotlinpractice.exercieseGivenByMentor.ServerResponse

class Server(private val ipAddress: String) {

    private val arrayOfUserNames = arrayOf("Atul", "Aaditya", "Aayush", "Aniket")

    fun fetchData(
        index: Int,
        onSuccess: (result: String) -> Unit,
        onFailure: (e: ServerExceptionCodes) -> Unit
    ) {
        try {
            if (ServerResponse.values().random() == ServerResponse.SUCCESS) {
                onSuccess(arrayOfUserNames[index])
                return
            }
        } catch (e: IndexOutOfBoundsException) {
            onFailure(ServerExceptionCodes.CODE_1001)
            return
        }

        val exceptionCode = ServerExceptionCodes.values().random()
        onFailure(exceptionCode)
    }

}
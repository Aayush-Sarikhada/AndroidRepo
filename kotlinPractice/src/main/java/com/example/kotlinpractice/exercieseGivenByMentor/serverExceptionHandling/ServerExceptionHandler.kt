package com.example.kotlinpractice.exercieseGivenByMentor.serverExceptionHandling

import com.example.kotlinpractice.exercieseGivenByMentor.ServerExceptionCodes

object ServerExceptionHandler {

    fun handle(code: ServerExceptionCodes) {
        when(code) {
            ServerExceptionCodes.CODE_404 -> println("Page not found!")
            ServerExceptionCodes.CODE_410 -> println("Page permanently gone!")
            ServerExceptionCodes.CODE_500 -> println("Internal Server Error Occurred")
            ServerExceptionCodes.CODE_503 -> println("Please authenticate yourself on network!")
            ServerExceptionCodes.CODE_501 -> println("Method not implemented for such requests!")
            ServerExceptionCodes.CODE_511 -> println("Please authenticate yourself on network!")
            ServerExceptionCodes.CODE_1001 -> println("Given index is out of bounds! please give a valid index")
        }
    }

}
package com.example.kotlinpractice.exercieseGivenByMentor.serverExceptionHandling

import com.example.kotlinpractice.exercieseGivenByMentor.ExerciseConstants

/*
Created By: Aayush Sarikhada
Updated on: 22 may 2023

This file contains example of Global Exception Handling with Lambda functions.
*/

fun main() {
    val server = Server(ExerciseConstants.SERVER_IP)
    val client = HttpClient(server)

    client.fetch(
        ExerciseConstants.VALID_INDEX,
        onSuccess = { result ->
            print("Success!, username is: $result")
        },
        onFailure = { serverExceptionCode ->
            ServerExceptionHandler.handle(serverExceptionCode)
        })
}
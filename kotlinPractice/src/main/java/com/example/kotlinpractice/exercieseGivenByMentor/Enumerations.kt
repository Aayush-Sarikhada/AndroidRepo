package com.example.kotlinpractice.exercieseGivenByMentor

enum class ServerExceptionCodes(val message: String) {
    CODE_404("Page Not Found"),
    CODE_410("Page Permanently Gone"),
    CODE_500("Internal Server Error"),
    CODE_503("Service Not Available"),
    CODE_501("Not Implemented"),
    CODE_511("Network Authentication required"),
    CODE_1001("Index Out Of Bound")
}

enum class ServerResponse {
    SUCCESS, FAILURE
}
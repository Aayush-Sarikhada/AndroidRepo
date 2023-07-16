package com.example.android.main.model

data class ScreenType<out T>(
    val id: Int,
    val name: String,
    val startActivity: Class<out T>
)

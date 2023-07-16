package com.example.android.main.model

import com.example.android.main.views.MainActivity

data class ScreenType(
    val id: Int,
    val name: String,
    val startActivity: Class<MainActivity>
)

package com.example.android.recyclerViewPractice.utils

import com.example.android.R

object Utils {
    private val listOfImgIds = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7
    )

    fun getAvatarImgId(): Int {
        return listOfImgIds.random()
    }
}
package com.example.recyclerViewPractice.models

import com.example.androidpracticeproject.R
import java.math.BigInteger

data class ArithmeticCardStateDataModel(
    val cardId: Int = 0,
    var strFirstEditTextValue: String = "0",
    var strSecondEditTextValue: String = "0",
    val spinnerStateList: MutableList<SpinnerStateDataModel> = mutableListOf(SpinnerStateDataModel()),
    val totalSpinnerRV: String = "0",
    val imagesIDsArray: MutableList<Int> = mutableListOf(R.drawable.avatar1),
    var total: BigInteger = BigInteger(("0"))
)

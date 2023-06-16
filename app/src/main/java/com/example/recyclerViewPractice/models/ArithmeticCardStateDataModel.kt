package com.example.recyclerViewPractice.models

import com.example.recyclerViewPractice.adapters.ArithmeticCardRVAdapter
import java.math.BigInteger

data class ArithmeticCardStateDataModel(
    val cardId: Int = 0,
    val strFirstEditTextValue: String = "",
    val strSecondEditTextValue: String = "",
    val spinnerStateList: List<SpinnerStateDataModel> = mutableListOf(SpinnerStateDataModel(10)),
    val total: String = "Total: ",
    val totalSpinnerRV: Long = 0,
    val imagesIDsArray: Int = 0
)

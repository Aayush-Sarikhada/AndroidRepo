package com.example.android.recyclerViewPractice.models

import com.example.android.R
import java.math.BigInteger

data class ArithmeticCardStateDataModel(
    val cardId: Int = 0,
    var strFirstEditTextValue: String = "0",
    var strSecondEditTextValue: String = "0",
    val spinnerStateList: MutableList<SpinnerStateDataModel> = mutableListOf(SpinnerStateDataModel()),
    var totalSpinnerRV: Int = 0,
    val imagesIDsArray: MutableList<Int> = mutableListOf(R.drawable.avatar1),
    var total: BigInteger = BigInteger("0")
) {
    fun updateTotal() {
        val spinnersTotal = updateSpinnerTotal()
        total = BigInteger(spinnersTotal) + BigInteger(strFirstEditTextValue) * BigInteger(
            strSecondEditTextValue
        )
    }

    private fun updateSpinnerTotal(): String {
        totalSpinnerRV = spinnerStateList.sumOf {
            it.spinnerValue
        }
        return totalSpinnerRV.toString()
    }
}

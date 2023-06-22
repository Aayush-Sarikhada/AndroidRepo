package com.example.recyclerViewPractice.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel

class ArithmeticCardDiffUtil(
    private val oldList: List<ArithmeticCardStateDataModel>,
    private val newList: List<ArithmeticCardStateDataModel>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].cardId == newList[newItemPosition].cardId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
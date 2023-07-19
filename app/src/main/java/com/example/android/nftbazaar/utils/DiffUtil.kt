package com.example.androidpracticeproject.utils

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.androidpracticeproject.models.home.NFTDataModel

class NFTDiffUtilCallback(private val oldList: List<NFTDataModel>, private val newList: List<NFTDataModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.d("DIFF_UTIL", "Old: $oldList")
        Log.d("DIFF_UTIL", "New: $newList")
        return oldList[oldItemPosition].nftId == newList[newItemPosition].nftId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
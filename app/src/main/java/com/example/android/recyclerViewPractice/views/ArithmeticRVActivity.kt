package com.example.android.recyclerViewPractice.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.databinding.ActivityArithmeticRecyclerViewBinding
import com.example.android.nftbazaar.utils.VerticalSpaceItemDecoration
import com.example.android.recyclerViewPractice.Constants
import com.example.android.recyclerViewPractice.PayloadTypes
import com.example.android.recyclerViewPractice.adapters.ArithmeticCardRVAdapter
import com.example.android.recyclerViewPractice.base.BaseActivity
import com.example.android.recyclerViewPractice.models.ArithmeticCardStateDataModel

class ArithmeticRVActivity : BaseActivity() {

    private lateinit var binding: ActivityArithmeticRecyclerViewBinding
    private var arithmeticCardRVDataList = mutableListOf(ArithmeticCardStateDataModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_arithmetic_recycler_view)
        setUpRvArithmeticCards()
    }

    private fun setUpRvArithmeticCards() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter =
            ArithmeticCardRVAdapter(this, arithmeticCardRVDataList, this::onDeleteBtnClicked) {
                arithmeticCardRVDataList.add(ArithmeticCardStateDataModel())
                binding.recyclerView.adapter!!.notifyItemInserted(arithmeticCardRVDataList.size - 1)
                binding.recyclerView.scrollToPosition(arithmeticCardRVDataList.size - 1)
            }
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(Constants.VERTICAL_SPACE_ARITHMETIC_CARD))
    }

    private fun onDeleteBtnClicked(position: Int) {
        arithmeticCardRVDataList.removeAt(position)
        binding.recyclerView.adapter?.notifyItemRemoved(position)
        if (position == (arithmeticCardRVDataList.size - 1)) {
            binding.recyclerView.adapter?.notifyItemChanged(
                position - 1, PayloadTypes.BTN_ADD_MORE_CARDS
            )
        }
    }

}
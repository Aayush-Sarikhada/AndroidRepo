package com.example.recyclerViewPractice.views

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpracticeproject.databinding.ActivityMainBinding
import com.example.androidpracticeproject.utils.VerticalSpaceItemDecoration
import com.example.recyclerViewPractice.Constants
import com.example.recyclerViewPractice.PayloadTypes
import com.example.recyclerViewPractice.adapters.ArithmeticCardRVAdapter
import com.example.recyclerViewPractice.base.BaseActivity
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var arithmeticCardRVDataList = mutableListOf(ArithmeticCardStateDataModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
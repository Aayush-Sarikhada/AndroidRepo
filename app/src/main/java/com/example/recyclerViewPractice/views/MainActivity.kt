package com.example.recyclerViewPractice.views

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var rvArithmeticCards: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvArithmeticCards = binding.recyclerView
        setUpRvArithmeticCards()
    }

    private fun setUpRvArithmeticCards() {
        rvArithmeticCards.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvArithmeticCards.adapter =
            ArithmeticCardRVAdapter(this, arithmeticCardRVDataList, this::onDeleteBtnClicked) {
                arithmeticCardRVDataList.add(ArithmeticCardStateDataModel())
                rvArithmeticCards.adapter?.notifyItemInserted(arithmeticCardRVDataList.size - 1)
                rvArithmeticCards.scrollToPosition(arithmeticCardRVDataList.size - 1)
            }
        rvArithmeticCards.addItemDecoration(VerticalSpaceItemDecoration(Constants.VERTICAL_SPACE_ARITHMETIC_CARD))
    }

    private fun onDeleteBtnClicked(position: Int) {
        val lastCardPos = arithmeticCardRVDataList.size - 1
        arithmeticCardRVDataList.removeAt(position)
        rvArithmeticCards.adapter?.notifyItemRemoved(position)
        if (position == lastCardPos) {
            rvArithmeticCards.adapter?.notifyItemChanged(
                position - 1, PayloadTypes.BTN_ADD_MORE_CARDS
            )
        }
    }

}
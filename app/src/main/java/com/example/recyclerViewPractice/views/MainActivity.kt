package com.example.recyclerViewPractice.views

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.databinding.ActivityMainBinding
import com.example.recyclerViewPractice.adapters.ArithmeticCardRVAdapter
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var arithmeticCardRVDataList = mutableListOf(ArithmeticCardStateDataModel())
    private lateinit var rvArithmeticCards: RecyclerView
    private lateinit var btnAddMore: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnAddMore = binding.btnAddMoreArithmeticCard
        rvArithmeticCards = binding.recyclerView
        setUpRvArithmeticCards()
        btnAddMore.setOnClickListener {
            arithmeticCardRVDataList.add(ArithmeticCardStateDataModel())
            rvArithmeticCards.adapter?.notifyItemInserted(arithmeticCardRVDataList.size - 1)
            updateConstraintsOfBtnAddMore()
        }

    }

    private fun updateConstraintsOfBtnAddMore() {
        if (arithmeticCardRVDataList.size < 2) {
            binding.btnAddMoreArithmeticCard.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.recyclerView.id
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
            }
        } else if (arithmeticCardRVDataList.size == 2) {
            binding.btnAddMoreArithmeticCard.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = ConstraintLayout.LayoutParams.UNSET
                bottomToBottom = binding.recyclerView.id
            }
        }
    }

    private fun setUpRvArithmeticCards() {
        rvArithmeticCards.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvArithmeticCards.adapter =
            ArithmeticCardRVAdapter(this, arithmeticCardRVDataList, this::onDeleteBtnClicked)
    }

    private fun onDeleteBtnClicked(position: Int) {
        arithmeticCardRVDataList.removeAt(position)
        rvArithmeticCards.adapter?.notifyItemRemoved(position)
        updateConstraintsOfBtnAddMore()
    }

}
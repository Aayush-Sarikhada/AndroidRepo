package com.example.recyclerViewPractice.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.ActivityMainBinding
import com.example.recyclerViewPractice.adapters.ArithmeticCardRVAdapter
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var arithmeticCardRVDataList = listOf(ArithmeticCardStateDataModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = ArithmeticCardRVAdapter(this, arithmeticCardRVDataList)
    }


}
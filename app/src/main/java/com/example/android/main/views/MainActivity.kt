package com.example.android.main.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.databinding.ActivityMainBinding
import com.example.android.main.adapter.UIComponentsRVAdapter
import com.example.android.main.model.ScreenType
import com.example.android.nftbazaar.views.AuthenticationActivity
import com.example.android.recyclerViewPractice.views.ArithmeticRVActivity
import com.example.android.stockexchangeui.StockExchangeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvActivities.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = UIComponentsRVAdapter(this@MainActivity, getActivitiesDataList())
        }

    }

    private fun getActivitiesDataList(): List<ScreenType<AppCompatActivity>> {
        return listOf(
            ScreenType(0, name = "Stock exchange UI", StockExchangeActivity::class.java),
            ScreenType(0, name = "NFT Bazaar", AuthenticationActivity::class.java),
            ScreenType(0, name = "Arithmetic Recycler View", ArithmeticRVActivity::class.java),
        )
    }
}
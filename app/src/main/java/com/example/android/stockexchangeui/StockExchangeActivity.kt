package com.example.android.stockexchangeui

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R
import com.example.android.stockexchangeui.adapters.StockSpinnerAdapter
import com.example.android.stockexchangeui.data.StockData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC

class StockExchangeActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var stockExchangeSpinner: Spinner
    private lateinit var stockReceiveSpinner: Spinner
    private lateinit var stockData: List<StockData>
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stockexchange)
        setUpUIComponents()
        stockData = getListOfStockData()
        setUpBottomNavigation()
        setUpTabLayout()
        setupSpinner()
    }

    private fun setUpUIComponents() {
        tabLayout = findViewById(R.id.tab_bar_layout)
        stockExchangeSpinner = findViewById(R.id.exchange_spinner)
        stockReceiveSpinner = findViewById(R.id.receive_spinner)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }

    private fun setUpBottomNavigation() {
        bottomNavigationView.background = null
    }

    private fun setupSpinner() {
        stockExchangeSpinner.adapter = StockSpinnerAdapter(this, stockData)
        stockReceiveSpinner.adapter = StockSpinnerAdapter(this, stockData.shuffled())
    }

    private fun setUpTabLayout() {
        val limitTab = tabLayout.newTab()
        tabLayout.tabGravity = TabLayout.GRAVITY_START
        tabLayout.addTab(limitTab)
        tabLayout.tabIndicatorAnimationMode = INDICATOR_ANIMATION_MODE_ELASTIC
        limitTab.id = R.string.tab_item_limit
        limitTab.text = getString(R.string.tab_item_limit)
        limitTab.select()

        val marketTab = tabLayout.newTab()
        marketTab.id = R.string.tab_item_market
        marketTab.text = getString(R.string.tab_item_market)
        tabLayout.addTab(marketTab)

        val poolTab = tabLayout.newTab()
        poolTab.id = R.string.tab_item_pool
        poolTab.text = getString(R.string.tab_item_pool)
        tabLayout.addTab(poolTab)

        val infoTab = tabLayout.newTab()
        infoTab.id = R.string.tab_item_info
        infoTab.text = getString(R.string.tab_item_info)
        tabLayout.addTab(infoTab)
    }

    private fun getListOfStockData(): List<StockData> {
        return listOf(
            StockData(getString(R.string.BOA)),
            StockData(getString(R.string.TCS)),
            StockData(getString(R.string.HDFC)),
            StockData(getString(R.string.ITC)),
            StockData(getString(R.string.AXIS)),
            StockData(getString(R.string.SBI))
        )
    }

}
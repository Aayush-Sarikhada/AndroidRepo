package com.example.androidpracticeproject.uicomponentsPractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.uicomponentsPractice.adapters.StockSpinnerAdapter
import com.example.androidpracticeproject.uicomponentsPractice.data.StockData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var stockExchangeSpinner: Spinner
    lateinit var stockReceiveSpinner: Spinner
    lateinit var stockData: List<StockData>
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tab_bar_layout)
        stockExchangeSpinner = findViewById(R.id.exchange_spinner)
        stockReceiveSpinner = findViewById(R.id.receive_spinner)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        stockData = getListOfStockData()
        setUpBottomNavigation()
        setUpTabLayout()
        setupSpinner()
    }

    private fun setUpBottomNavigation() {
        bottomNavigationView.background = null
    }

    private fun setupSpinner() {
        stockExchangeSpinner.adapter = StockSpinnerAdapter(this,stockData)
        stockReceiveSpinner.adapter = StockSpinnerAdapter(this,stockData.shuffled())
        var clickListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//              Toast.makeText(this@MainActivity, stockData[position].stockName, Toast.LENGTH_SHORT).show()
                Snackbar.make(view!! , R.string.picked_stock_snack_bar,Snackbar.LENGTH_SHORT)
                    .setAction("Dismiss"){}
                    .setActionTextColor(getColor(R.color.white))
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        stockExchangeSpinner.onItemSelectedListener = clickListener
        stockReceiveSpinner.onItemSelectedListener = clickListener
    }

    private fun setUpTabLayout() {
        val limitTab = tabLayout.newTab()
        tabLayout.tabGravity = TabLayout.GRAVITY_START
        tabLayout.addTab(limitTab)
        tabLayout.tabIndicatorAnimationMode = INDICATOR_ANIMATION_MODE_ELASTIC
        limitTab.id = R.string.tablayout_item_limit
        limitTab.text = getString(R.string.tablayout_item_limit)
        limitTab.select()

        val marketTab = tabLayout.newTab()
        marketTab.id = R.string.tablayout_item_market
        marketTab.text = getString(R.string.tablayout_item_market)
        tabLayout.addTab(marketTab)

        val poolTab = tabLayout.newTab()
        poolTab.id = R.string.tablayout_item_pool
        poolTab.text = getString(R.string.tablayout_item_pool)
        tabLayout.addTab(poolTab)

        val infoTab = tabLayout.newTab()
        infoTab.id = R.string.tablayout_item_info
        infoTab.text = getString(R.string.tablayout_item_info)
        tabLayout.addTab(infoTab)
    }

    private fun getListOfStockData(): List<StockData> {
        return listOf(
            StockData("ABC1"),
            StockData("ABC2"),
            StockData("ABC3"),
            StockData("ABC4"),
            StockData("ABC5"),
            StockData("ABC6")
        )
    }

}
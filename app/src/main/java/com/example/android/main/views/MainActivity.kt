package com.example.android.main.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.R
import com.example.android.databinding.ActivityMainBinding
import com.example.android.main.adapter.UIComponentsRVAdapter
import com.example.android.main.model.ScreenType

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
            adapter = UIComponentsRVAdapter(getActivitiesDataList())
        }

    }

    private fun getActivitiesDataList(): List<ScreenType> {
        return listOf(
            ScreenType(0, name = "temp1", MainActivity::class.java),
            ScreenType(1, name = "temp2", MainActivity::class.java),
            ScreenType(2, name = "temp3", MainActivity::class.java),
            ScreenType(3, name = "temp4", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
            ScreenType(4, name = "temp5", MainActivity::class.java),
        )
    }
}
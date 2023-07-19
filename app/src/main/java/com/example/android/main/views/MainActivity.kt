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
            adapter = UIComponentsRVAdapter(this@MainActivity, ScreenType.getActivitiesDataList())
        }

    }

}
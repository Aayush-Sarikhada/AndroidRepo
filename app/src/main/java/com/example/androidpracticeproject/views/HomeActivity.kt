package com.example.androidpracticeproject.views

import android.os.Bundle
import android.widget.Toast
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.base.BaseActivity
import com.example.androidpracticeproject.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppToolbar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.log_out -> {
                    // Code to be executed when the add button is clicked
                    Toast.makeText(this, "Log out pressed", Toast.LENGTH_SHORT).show()
                }
                R.id.search -> {
                    // Code to be executed when the add button is clicked
                    Toast.makeText(this, "Search button pressed", Toast.LENGTH_SHORT).show()
                }
                R.id.notification -> {
                    // Code to be executed when the add button is clicked
                    Toast.makeText(this, "Notification button pressed", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

}
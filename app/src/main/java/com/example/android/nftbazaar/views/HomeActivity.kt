package com.example.android.nftbazaar.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R
import com.example.android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

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
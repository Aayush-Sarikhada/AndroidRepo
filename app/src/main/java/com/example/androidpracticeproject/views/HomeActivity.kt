package com.example.androidpracticeproject.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpracticeproject.Constant
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.ActivityHomeBinding

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
                    getSharedPreferences(Constant.PREFERENCE, MODE_PRIVATE).edit().putBoolean(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN, false).apply()
                    startActivity(Intent(this, AuthenticationActivity::class.java))
                    finish()
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
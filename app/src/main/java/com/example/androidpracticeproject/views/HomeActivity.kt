package com.example.androidpracticeproject.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityHomeBinding.inflate(layoutInflater).root)
    }
}
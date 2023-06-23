package com.example.activitiesandfragments.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.activitiesandfragments.ActivityEnum
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.ActivityMainBinding
import com.example.activitiesandfragments.fragments.HomeScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFragment()
    }

    private fun setUpFragment() {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, HomeScreenFragment())
            .commitNow()
    }

    override fun onResume() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Resumed")
        super.onResume()
    }

    override fun onAttachedToWindow() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Attached to window")
        super.onAttachedToWindow()
    }

    override fun onStart() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Started")
        super.onStart()
    }

    override fun onStop() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Stopped")
        super.onStop()
    }

    override fun onPause() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Paused")
        super.onPause()
    }

    override fun onDetachedFromWindow() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN,"Detached from window")
        super.onDetachedFromWindow()
    }

}
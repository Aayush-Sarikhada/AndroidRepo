package com.example.activitiesandfragments.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.ActivityMainBinding
import com.example.activitiesandfragments.fragments.ComponentsFragment
import com.example.activitiesandfragments.fragments.UIScreensFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "On Create")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setUpActionBar()
        setContentView(binding.root)
        setUpBottomNavigation()
        setUpFragment()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = if(binding.bottomNavView.selectedItemId == R.id.uiScreens) {
            getString(R.string.action_bar_title_ui_screens)
        } else {
            getString(R.string.action_bar_title_components)
        }
    }

    private fun setUpBottomNavigation() {
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.uiScreens -> {
                    supportActionBar?.title = getString(R.string.action_bar_title_ui_screens)
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, UIScreensFragment())
                        .commit()
                    true
                }

                R.id.components -> {
                    supportActionBar?.title = getString(R.string.action_bar_title_components)
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ComponentsFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

    private fun setUpFragment() {
        fragmentManager = supportFragmentManager
        if(binding.bottomNavView.selectedItemId == R.id.uiScreens) {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, UIScreensFragment())
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, ComponentsFragment())
                .commit()
        }
    }


    override fun onDestroy() {
        return
        Log.d("LIFE_CYCLE","ON Destroy")
        super.onDestroy()
    }

    override fun onResume() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Resumed")
        super.onResume()
    }

    override fun onAttachedToWindow() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Attached to window")
        super.onAttachedToWindow()
    }

    override fun onStart() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Started")
        super.onStart()
    }

    override fun onStop() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Stopped")
        super.onStop()
    }

    override fun onPause() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Paused")
        super.onPause()
    }

    override fun onDetachedFromWindow() {
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Detached from window")
        super.onDetachedFromWindow()
    }

}
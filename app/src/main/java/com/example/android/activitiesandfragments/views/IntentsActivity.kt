package com.example.android.activitiesandfragments.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.android.R
import com.example.android.activitiesandfragments.Constants
import com.example.android.activitiesandfragments.fragments.ComponentsFragment
import com.example.android.activitiesandfragments.fragments.UIScreensFragment
import com.example.android.databinding.ActivityIntentsBinding

class IntentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentsBinding
    private lateinit var fragmentManager: FragmentManager

    companion object {
        const val REQUEST_KEY = "REQUEST_KEY_MAIN_ACTIVITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intents)
        setUpActionBar()
        setContentView(binding.root)
        setUpBottomNavigation()
        setUpFragment()
        setUpFragmentManagerListener()
    }

    private fun setUpFragmentManagerListener() {
        fragmentManager.setFragmentResultListener(REQUEST_KEY, this) { requestKey, bundle ->
            Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Request key: $requestKey")
            Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Email: ${bundle.getStringArray("EMAIL")}")
            Log.d(
                Constants.Tags.TAG_ACTIVITY_MAIN,
                "Email Message: ${bundle.getString("EMAIL_MESSAGE")}"
            )
            Log.d(
                Constants.Tags.TAG_ACTIVITY_MAIN,
                "Email Subject: ${bundle.getString("EMAIL_SUBJECT")}"
            )
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.title = if (binding.bottomNavView.selectedItemId == R.id.uiScreens) {
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
        if (binding.bottomNavView.selectedItemId == R.id.uiScreens) {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, UIScreensFragment())
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, ComponentsFragment())
                .commit()
        }
    }

    override fun finish() {
        super.finish()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Finish")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Resumed")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "On Destroy")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Attached to window")
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Started")
    }

    public override fun onStop() {
        super.onStop()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Stopped")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Paused")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(Constants.Tags.TAG_ACTIVITY_MAIN, "Detached from window")
    }

}
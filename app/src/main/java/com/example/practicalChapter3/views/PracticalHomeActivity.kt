package com.example.practicalChapter3.views

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.ActivityPracticalHomeBinding
import com.example.practicalChapter3.Constants
import com.example.practicalChapter3.fragments.GalleryFragment
import com.example.practicalChapter3.fragments.MessageReceiverFragment
import com.example.practicalChapter3.fragments.MessageSenderFragment

class PracticalHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticalHomeBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_practical_home)
        setContentView(binding.root)
        fragmentManager = supportFragmentManager
        setUpActionBar()
        setUpInitialFragment()
        setUpOnClickListeners()
    }

    private fun setUpActionBar() {
        supportActionBar?.title = Constants.Strings.ACTION_BAR_TITLE_PRACTICAL
    }

    private fun setUpInitialFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewID, GalleryFragment())
            .commit()
    }

    private fun setUpOnClickListeners() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.galleryFragment -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, GalleryFragment())
                        .commit()
                    true
                }

                R.id.messageSenderFragment -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, MessageSenderFragment())
                        .commit()
                    true
                }

                R.id.messageReceiverFragment -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, MessageReceiverFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}
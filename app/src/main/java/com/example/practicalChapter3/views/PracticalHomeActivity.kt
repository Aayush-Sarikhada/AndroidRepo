package com.example.practicalChapter3.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
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
        binding = ActivityPracticalHomeBinding.inflate(layoutInflater)
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
                R.id.gallery -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, GalleryFragment())
                        .commit()
                    true
                }

                R.id.addMessage -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, MessageSenderFragment())
                        .commit()
                    true
                }

                R.id.viewMessage -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewID, MessageReceiverFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

}
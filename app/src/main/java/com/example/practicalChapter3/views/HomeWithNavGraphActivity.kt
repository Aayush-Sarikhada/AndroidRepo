package com.example.practicalChapter3.views

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.ActivityHomeWithNavGraphBinding
import com.example.practicalChapter3.Constants

class HomeWithNavGraphActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeWithNavGraphBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_with_nav_graph)
        setContentView(binding.root)
        setUpNavController()
        setUpActionBar()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewID) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun setUpActionBar() {
        supportActionBar?.title = Constants.Strings.ACTION_BAR_TITLE_PRACTICAL
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
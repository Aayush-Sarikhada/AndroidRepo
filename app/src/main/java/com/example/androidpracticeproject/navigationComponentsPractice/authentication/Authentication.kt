package com.example.androidpracticeproject.navigationComponentsPractice.authentication

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.ActivityAuthenticationBinding


class Authentication : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(ActivityAuthenticationBinding.inflate(layoutInflater).root)
        supportFragmentManager.beginTransaction().add(LoginFragment(),"").commit()

    }
}
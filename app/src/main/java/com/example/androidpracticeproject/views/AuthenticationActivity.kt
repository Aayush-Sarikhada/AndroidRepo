package com.example.androidpracticeproject.views

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androidpracticeproject.databinding.ActivityAuthenticationBinding
import com.example.androidpracticeproject.fragments.authentication.LoginFragment


class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(ActivityAuthenticationBinding.inflate(layoutInflater).root)
    }

}
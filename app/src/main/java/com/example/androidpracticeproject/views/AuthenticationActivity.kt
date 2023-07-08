package com.example.androidpracticeproject.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpracticeproject.Constant
import com.example.androidpracticeproject.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = getSharedPreferences(Constant.PREFERENCE, Context.MODE_PRIVATE)
        if(pref.contains(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN)) {
            if(pref.getBoolean(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN, false)) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
        setContentView(ActivityAuthenticationBinding.inflate(layoutInflater).root)
    }

}
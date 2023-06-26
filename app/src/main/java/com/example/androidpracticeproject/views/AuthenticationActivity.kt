package com.example.androidpracticeproject.views

import android.os.Bundle
import com.example.androidpracticeproject.base.BaseActivity
import com.example.androidpracticeproject.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityAuthenticationBinding.inflate(layoutInflater).root)
    }

}
package com.example.webservices.adequateshopuserapi.views

import android.os.Bundle
import com.example.javapractice.R
import com.example.webservices.adequateshopuserapi.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }
}
package com.example.android.nftbazaar.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAuthenticationBinding>(
            this,
            R.layout.activity_authentication
        )
    }

}
package com.example.android.nftbazaar.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android.R
import com.example.android.databinding.ActivityAuthenticationBinding
import com.example.android.nftbazaar.base.BaseActivity

class AuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAuthenticationBinding>(
            this,
            R.layout.activity_authentication
        )
    }

}
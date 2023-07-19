package com.example.android.nftbazaar.views

import android.content.Intent
import android.os.Bundle
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.databinding.ActivityHomeBinding
import com.example.android.nftbazaar.Constant

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_log_out -> {
                    getSharedPreferences(Constant.PREFERENCE, MODE_PRIVATE).edit()
                        .putBoolean(Constant.Keys.SP_KEY_IS_USER_LOGGED_IN, false).apply()
                    startActivity(Intent(this, AuthenticationActivity::class.java))
                    finish()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

}
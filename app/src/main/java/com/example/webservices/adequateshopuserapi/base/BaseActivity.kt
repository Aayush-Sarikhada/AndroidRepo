package com.example.webservices.adequateshopuserapi.base

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("BASE_ACTIVITY", "Touch event dispatched")
        if (currentFocus != null) {
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}
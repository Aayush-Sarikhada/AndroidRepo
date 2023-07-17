package com.example.androidpracticeproject.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.hideKeyboardOnTouchOutside() {
    val focusedView = currentFocus
    if (focusedView is EditText) {
        focusedView.clearFocus()
        val inputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0)
    }
}
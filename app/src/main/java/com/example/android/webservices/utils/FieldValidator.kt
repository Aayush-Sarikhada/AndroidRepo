package com.example.android.webservices.utils

import android.util.Patterns

object FieldValidator {

    fun isFullNameValid(name: String): Boolean {
        return name.trim().isNotEmpty()
    }

    fun isEmailValid(email: String): Boolean {
        return (email.trim().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    fun isPasswordValid(password: String): Boolean {
        return (password.trim().isNotEmpty() && (6..8).contains(password.length))
    }

}
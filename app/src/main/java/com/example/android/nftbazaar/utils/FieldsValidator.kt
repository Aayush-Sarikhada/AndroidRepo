package com.example.android.nftbazaar.utils

object FieldsValidator {

    fun validatePassword(password: String): Boolean {
        return password.trim().length >= 8
    }

    fun validateEmail(email: String): Boolean {
        return email.trim().isNotEmpty()
    }

    fun validateName(name: String): Boolean {
        return name.trim().isNotEmpty()
    }

}
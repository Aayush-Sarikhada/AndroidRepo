package com.example.android.nftbazaar.authentication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.android.R

class OtpVerificationDialog(private val context: Context, private val mobileNumber: String) :
    Dialog(context) {

    private var TAG = "OTP_DIALOG"
    private lateinit var otpEt1: EditText
    private lateinit var otpEt2: EditText
    private lateinit var otpEt3: EditText
    private lateinit var otpEt4: EditText
    private lateinit var tvResendBtn: TextView
    private lateinit var btnVerify: Button
    private lateinit var tvMobileNumber: TextView

    private var selectedETPosition = 0

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                if (s.isNotEmpty()) {
                    when (selectedETPosition) {
                        0 -> {
                            selectedETPosition = 1
                            showKeyboard(otpEt2)
                        }

                        1 -> {
                            selectedETPosition = 2
                            showKeyboard(otpEt3)
                        }

                        2 -> {
                            selectedETPosition = 3

                            showKeyboard(otpEt4)
                        }
                    }
                }
            }
        }

    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            when (selectedETPosition) {
                3 -> {
                    selectedETPosition = 2
                    showKeyboard(otpEt3)
                }

                2 -> {
                    selectedETPosition = 1
                    showKeyboard(otpEt2)
                }

                1 -> {
                    selectedETPosition = 0
                    showKeyboard(otpEt1)
                }
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_otp)
        otpEt1 = findViewById(R.id.otp_et1)
        otpEt2 = findViewById(R.id.otp_et2)
        otpEt3 = findViewById(R.id.otp_et3)
        otpEt4 = findViewById(R.id.otp_et4)
        tvResendBtn = findViewById(R.id.resendBtn)
        btnVerify = findViewById(R.id.verify_button)
        tvMobileNumber = findViewById(R.id.mobile_number)

        otpEt1.addTextChangedListener(textWatcher)
        otpEt2.addTextChangedListener(textWatcher)
        otpEt3.addTextChangedListener(textWatcher)
        otpEt4.addTextChangedListener(textWatcher)

        tvMobileNumber.text = mobileNumber
        btnVerify.isEnabled = true
        // by default show keyboard on first edit text
        showKeyboard(otpEt1)

        btnVerify.setOnClickListener {
            val strOTP =
                otpEt1.text.toString() + otpEt2.text.toString() + otpEt3.text.toString() + otpEt4.text.toString()
            Log.d(TAG, "OTP String : $strOTP")
            if (strOTP.length == 4) {
                Toast.makeText(context, strOTP, Toast.LENGTH_SHORT).show()
                this.dismiss()
            }
        }
    }

    private fun showKeyboard(otpEt: EditText) {
        otpEt.requestFocus()

        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(otpEt, InputMethodManager.SHOW_IMPLICIT)
    }


}
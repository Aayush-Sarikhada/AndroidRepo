package com.example.androidpracticeproject.fragments.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.FragmentForgotPasswordBinding
import com.example.androidpracticeproject.components.authentication.OTPverificationDialog

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    private var btnSendOTP: Button? = null
    private var etMobileNumber: EditText? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendOTP = view.findViewById(R.id.Send_otp_button)
        etMobileNumber = view.findViewById(R.id.et_mobile_number)
        btnSendOTP?.setOnClickListener {
            if (checkMobileNumberValidity()) {
                val otpVerificationDialog =
                    OTPverificationDialog(requireContext(), etMobileNumber?.text.toString())
                otpVerificationDialog.setCancelable(false)
                otpVerificationDialog.show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun checkMobileNumberValidity(): Boolean {
        // TODO: CHECK MOBILE NUMBER VALIDITY
        return true
    }

}
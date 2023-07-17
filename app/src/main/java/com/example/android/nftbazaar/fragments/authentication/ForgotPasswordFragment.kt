package com.example.android.nftbazaar.fragments.authentication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.databinding.FragmentForgotPasswordBinding
import com.example.android.nftbazaar.authentication.OtpVerificationDialog

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var btnSendOTP: Button
    private lateinit var etMobileNumber: EditText
    private lateinit var btnBackToSignIn: Button
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSendOTP = binding.SendOtpButton
        etMobileNumber = binding.etMobileNumber
        btnBackToSignIn = binding.backToSignIn
        navController = findNavController()
        focusOnEtMobileNumber()
        btnSendOTP.setOnClickListener {
            if (checkMobileNumberValidity()) {
                val otpVerificationDialog =
                    OtpVerificationDialog(requireContext(), etMobileNumber.text.toString())
                otpVerificationDialog.setCancelable(false)
                otpVerificationDialog.show()
            }
        }

        btnBackToSignIn.setOnClickListener {
            navController.popBackStack()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun checkMobileNumberValidity(): Boolean {
        return true
    }

    private fun focusOnEtMobileNumber() {
        etMobileNumber.requestFocus()
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(etMobileNumber, InputMethodManager.SHOW_IMPLICIT)
    }

}
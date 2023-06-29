package com.example.activitiesandfragments.fragments

import android.content.Context
import android.content.Intent
import android.location.Address
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.activitiesandfragments.Constants.Keys.BUNDLE_KEY_EMAIL
import com.example.activitiesandfragments.Constants.Keys.EMAIL_MESSAGE
import com.example.activitiesandfragments.Constants.Keys.EMAIL_SUBJECT
import com.example.activitiesandfragments.databinding.FragmentImplicitIntentsBinding
import com.example.activitiesandfragments.views.MainActivity
import java.net.URI

class ImplicitIntentsFragment : Fragment() {

    private lateinit var binding: FragmentImplicitIntentsBinding

    companion object {
        val TAG = "LIFE_CYCLE"
    }

    override fun onPause() {
        Log.d(TAG, "fragment on pause")
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "fragment On View Created")
        binding.btnSendText.setOnClickListener {
            val msg = binding.etMessage.text.toString()
            if (msg.isNotEmpty()) {
                val msgIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, msg)
                }
                val shareIntent = Intent.createChooser(msgIntent,null)
                startActivity(shareIntent)
            }
        }

        binding.btnSendEmail.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val emailSub = binding.etEmailSubject.text.toString()
            val emailMsg = binding.etEmailMessage.text.toString()

            if (emailMsg.isNotEmpty() && emailSub.isNotEmpty() && email.isNotEmpty()) {
                composeEmail(arrayOf(email),emailSub,emailMsg)
            }
        }

    }

    private fun composeEmail(addresses: Array<String>, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        val bundle = Bundle().apply {
            putStringArray(BUNDLE_KEY_EMAIL , addresses)
            putString(EMAIL_MESSAGE, message)
            putString(EMAIL_SUBJECT, subject)
        }
        setFragmentResult(MainActivity.REQUEST_KEY, bundle)
        startActivity(intent)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "fragment on Detach")
    }

    override fun onDestroyView() {
        Log.d(TAG, "fragment On Destroy view")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "fragment On Destroy")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "fragment On Create")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "fragment on Attach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "fragment On Create View")
        binding = FragmentImplicitIntentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
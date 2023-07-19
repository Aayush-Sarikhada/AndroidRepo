package com.example.android.activitiesandfragments.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.android.activitiesandfragments.views.IntentsActivity
import com.example.android.databinding.FragmentImplicitIntentsBinding

class ImplicitIntentsFragment : Fragment() {

    private lateinit var binding: FragmentImplicitIntentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSendText.setOnClickListener {
            val msg = binding.etMessage.text.toString()
            if (msg.isNotEmpty()) {
                val msgIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, msg)
                }
                val shareIntent = Intent.createChooser(msgIntent, null)
                startActivity(shareIntent)
            }
        }

        binding.btnSendEmail.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val emailSub = binding.etEmailSubject.text.toString()
            val emailMsg = binding.etEmailMessage.text.toString()
            if (emailMsg.isNotEmpty() && emailSub.isNotEmpty() && email.isNotEmpty()) {
                composeEmail(arrayOf(email), emailSub, emailMsg)
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
            putStringArray("EMAIL", addresses)
            putString("EMAIL_MESSAGE", message)
            putString("EMAIL_SUBJECT", subject)
        }
        setFragmentResult(IntentsActivity.REQUEST_KEY, bundle)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImplicitIntentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
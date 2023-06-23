package com.example.activitiesandfragments.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.databinding.FragmentImplicitIntentsBinding

class ImplicitIntentsFragment : Fragment() {

    private lateinit var binding: FragmentImplicitIntentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSendText.setOnClickListener {
            val msg = binding.etMessage.text
            if (msg != null) {
                if (msg.isNotEmpty()) {
                    val msgIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, msg)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(msgIntent, null)
                    startActivity(shareIntent)
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImplicitIntentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
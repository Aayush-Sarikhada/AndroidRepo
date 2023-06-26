package com.example.activitiesandfragments.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.databinding.FragmentImplicitIntentsBinding

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
        Log.d(TAG,"fragment On View Created")
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

        binding.btnSendEmail
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"fragment on Detach")
    }

    override fun onDestroyView() {
        Log.d(TAG,"fragment On Destroy view")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG,"fragment On Destroy")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"fragment On Create")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"fragment on Attach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"fragment On Create View")
        binding = FragmentImplicitIntentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
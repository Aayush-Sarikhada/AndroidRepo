package com.example.practicalChapter3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.activitiesandfragments.databinding.FragmentSendMessageBinding
import com.example.practicalChapter3.Constants
import com.example.practicalChapter3.viewModels.SharedViewModel

class MessageSenderFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageBinding
    private lateinit var viewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        viewModel.listOfMessage.observe(requireActivity()){
            Log.d(Constants.Tags.MESSAGE_SENDER_FRAGMENT,it.toString())
        }
        setUpOnClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendMessageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setUpOnClickListeners() {
        binding.btnSendMessage.setOnClickListener {
            val message = binding.etYourMessage.text
            if(!message.isNullOrEmpty()) {
                viewModel.addToList(message.trim().toString())
            }
            viewModel.setSomeData(25)
        }
    }

}
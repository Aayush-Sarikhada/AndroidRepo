package com.example.android.practical3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.R
import com.example.android.databinding.FragmentSendMessageBinding
import com.example.android.practical3.Constants
import com.example.android.practical3.viewModels.SharedViewModel

class MessageSenderFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_send_message,
            container,
            false
        )
        return binding.root
    }

    private fun setUpObservers() {
        viewModel.previewMessage.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
        binding.viewModel = viewModel
    }

    private fun setUpOnClickListeners() {
        binding.fabSendMessage.setOnClickListener {
            val message = binding.etYourMessage.text
            if (!message.isNullOrEmpty()) {
                viewModel.addToList(message.trim().toString())
                Toast.makeText(
                    requireContext(),
                    Constants.Strings.TOAST_MESSAGE_SAVED,
                    Toast.LENGTH_SHORT
                ).show()
                binding.etYourMessage.text?.clear()
            } else {
                Toast.makeText(
                    requireContext(),
                    Constants.Strings.TOAST_MESSAGE_NOT_SENT,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
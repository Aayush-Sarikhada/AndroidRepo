package com.example.practicalChapter3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.FragmentMessageReceiverBinding
import com.example.practicalChapter3.viewModels.SharedViewModel

class MessageReceiverFragment : Fragment() {

    private lateinit var binding: FragmentMessageReceiverBinding
    private lateinit var viewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        setUpListViewData()
        setUpObservers()
        setUpOnClickListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_message_receiver,container,false)
        return binding.root
    }

    private fun setUpListViewData() {
        val dataList = viewModel.listOfMessage.value
        dataList?.let {
            binding.messagesList.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
        }
    }

    private fun setUpObservers() {
        viewModel.listOfMessage.observe(viewLifecycleOwner) {
            binding.messagesList.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
        }
    }

    private fun setUpOnClickListener() {
        binding.fabClearList.setOnClickListener {
            viewModel.clearList()
        }
    }
}
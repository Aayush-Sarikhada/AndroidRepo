package com.example.practicalChapter3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.activitiesandfragments.databinding.FragmentMessageReceiverBinding
import com.example.practicalChapter3.Constants
import com.example.practicalChapter3.viewModels.SharedViewModel

class MessageReceiverFragment : Fragment() {

    private lateinit var binding: FragmentMessageReceiverBinding
    private lateinit var viewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        setUpListViewData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageReceiverBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setUpListViewData() {
        val dataList = viewModel.listOfMessage.value
        dataList?.let {
            binding.messagesList.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
            Log.d(Constants.Tags.MESSAGE_RECEIVER_FRAGMENT, viewModel.someData.value.toString())
        }
    }

}
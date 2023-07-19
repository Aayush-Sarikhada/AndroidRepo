package com.example.android.activitiesandfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.databinding.FragmentUiScreensBinding

class UIScreensFragment : Fragment() {
    // TODO: add code once other branches are merged and rebased in this branch
    private lateinit var binding: FragmentUiScreensBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUiScreensBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
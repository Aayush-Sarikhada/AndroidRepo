package com.example.activitiesandfragments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.btnIntents.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,ImplicitIntentsFragment())
                .commitNow()
            Log.d("HomeScreenFragment",this::class.simpleName!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
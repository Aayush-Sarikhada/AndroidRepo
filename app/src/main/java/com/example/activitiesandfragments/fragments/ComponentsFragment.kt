package com.example.activitiesandfragments.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.FragmentComponentsBinding

class ComponentsFragment : Fragment() {

    private lateinit var binding: FragmentComponentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: onViewCreated")
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.btnIntents.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ImplicitIntentsFragment())
                .commit()
        }

        binding.btnImagePicker.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, ImagePickerFragment())
                .commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: on Detach")
    }

    override fun onDestroyView() {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: On Destroy view")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: On Destroy")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: On Create")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: on Attach")
    }

    override fun onPause() {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: on pause")
        super.onPause()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: onCreateView")
        binding = FragmentComponentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
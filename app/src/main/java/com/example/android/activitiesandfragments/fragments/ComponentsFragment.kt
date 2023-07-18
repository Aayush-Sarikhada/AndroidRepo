package com.example.android.activitiesandfragments.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.R
import com.example.android.activitiesandfragments.Constants
import com.example.android.activitiesandfragments.Constants.Strings.URL_FACEBOOK
import com.example.android.databinding.FragmentComponentsBinding

class ComponentsFragment : Fragment() {

    private lateinit var binding: FragmentComponentsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: onViewCreated")
        setUpOnClickListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(Constants.Tags.LIFE_CYCLE, "ComponentFragment: on Attach")
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

        binding.btnWebBrowser.setOnClickListener {
            openBrowserActivity(URL_FACEBOOK)
        }

        binding.btnPendingIntent.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, PendingIntentFragment())
                .commit()
        }
    }

    private fun openBrowserActivity(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        Intent.createChooser(intent, Constants.Strings.CHOOSE_APP)
        startActivity(intent)
    }

}
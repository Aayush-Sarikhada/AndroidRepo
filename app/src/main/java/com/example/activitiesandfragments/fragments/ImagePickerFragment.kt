package com.example.activitiesandfragments.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.adapter.ImageListAdapter
import com.example.activitiesandfragments.databinding.FragmentImagePickerBinding

class ImagePickerFragment : Fragment() {

    private lateinit var binding: FragmentImagePickerBinding
    private var uriList: MutableList<Uri> = mutableListOf()

    private val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri == null) {
            Log.d(Constants.Tags.TAG_IMAGE_PICKER_FRAGMENT, "URI: empty")
        } else {
            Log.d(Constants.Tags.TAG_IMAGE_PICKER_FRAGMENT, "URI: $uri")
            uriList.add(uri)
            setUpImageList()
        }
    }

    private val captureImage = registerForActivityResult(ActivityResultContracts.TakePicture()) {isSuccess ->
        if(isSuccess) {
            // TODO: Remaining
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        setUpImageList()
    }

    private fun setUpOnClickListeners() {
        binding.btnCaptureImage.setOnClickListener {

        }

        binding.btnImagePick.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private fun setUpImageList() {
        binding.lvImages.adapter = ImageListAdapter(requireContext(), uriList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagePickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
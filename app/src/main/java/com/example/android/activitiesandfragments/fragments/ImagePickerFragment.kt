package com.example.android.activitiesandfragments.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.android.activitiesandfragments.adapter.ImageListAdapter
import com.example.android.databinding.FragmentImagePickerBinding

class ImagePickerFragment : Fragment() {

    private lateinit var binding: FragmentImagePickerBinding
    private var uriList: MutableList<Uri> = mutableListOf()

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) {
            } else {
                uriList.add(uri)
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = uri
                })
                setUpImageList()
            }
        }

    private val captureImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val thumbnail: Bitmap? = activityResult.data?.getParcelableExtra("data")
                binding.imgPicked.setImageBitmap(thumbnail)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        setUpImageList()
    }

    private fun setUpOnClickListeners() {
        binding.btnCaptureImage.setOnClickListener {
            captureImage.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
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
package com.example.webservices.adequateshopuserapi.views.imageupload

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivityImageUploadBinding

class ImageUploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageUploadBinding
    private lateinit var imageUri: Uri
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "uri: $uri")
                binding.imgUpload.setImageURI(uri)
                imageUri = uri
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_upload)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        binding.btnUpload.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

}
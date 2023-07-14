package com.example.webservices.adequateshopuserapi.views.imageupload

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.javapractice.R
import com.example.javapractice.databinding.ActivityImageUploadBinding
import com.example.webservices.adequateshopuserapi.viewmodels.ImageUploadViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ImageUploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageUploadBinding
    private var imageUri: Uri? = null
    private var imageUrl = ""
    private val viewModel: ImageUploadViewModel by viewModels()
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("IMAGE_UPLOAD_ACTIVITY", "uri: $uri")
                binding.imgUpload.setImageURI(uri)
                imageUri = uri
                binding.progressImgUpload.progress = 0
            } else {
                Log.d("IMAGE_UPLOAD_ACTIVITY", "No media selected")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_upload)
        setUpOnClickListeners()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.imageViewUrl.observe(this) {
            imageUrl = it
        }

        viewModel.progress.observe(this) {
            binding.progressImgUpload.setProgress(it.toInt(),true)
        }

        viewModel.imageResponse.observe(this) {success->
            if(success) {
                Toast.makeText(this, "Image Upload Successful!", Toast.LENGTH_SHORT).show()
                binding.btnSeeImage.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpOnClickListeners() {
        binding.btnSelectImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btnUploadImage.setOnClickListener {
            imageUri?.let {uri->
                val filesDir = applicationContext.filesDir
                val file = File(filesDir, "image.png")
                Log.d("IMAGE_UPLOAD_ACTIVITY", "URI: $uri")
                contentResolver.openInputStream(uri).use {inputStream ->
                    val outputStream = FileOutputStream(file)
                    inputStream?.copyTo(outputStream)
                }
                viewModel.uploadImage(file)
            }
        }

        binding.btnSeeImage.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl)))
        }
    }

}
package com.example.practicalChapter3.fragments

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.R
import com.example.activitiesandfragments.databinding.FragmentGalleryBinding
import com.example.practicalChapter3.Constants
import com.example.practicalChapter3.Constants.DataFormats.YYYYMMDD_HHMMSS
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var mPhotoUri: Uri
    private val launchCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                binding.imgPickedWithFab.setImageURI(mPhotoUri)
            } else {
                Toast.makeText(
                    requireContext(),
                    Constants.Strings.TOAST_IMAGE_NOT_AVAILABLE,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    private val pickImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                setImageView(uri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_gallery,
            container,
            false
        )
        return binding.root
    }

    private fun setUpOnClickListeners() {
        binding.fabPickImage.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.fabPickImage.setOnLongClickListener {
            binding.fabClickImage.let { fabClickImage ->
                fabClickImage.visibility =
                    if (fabClickImage.isGone) FloatingActionButton.VISIBLE else FloatingActionButton.GONE
            }
            true
        }

        binding.fabClickImage.setOnClickListener {
            clickImage()
        }
    }

    private fun setImageView(uri: Uri) {
        binding.imgPickedWithFab.setImageURI(uri)
    }

    private fun clickImage() {
        val photoFile = createImageFile()
        photoFile.let {
            mPhotoUri = FileProvider.getUriForFile(
                requireContext(),
                "${activity?.applicationContext?.packageName}.fileProvider",
                it
            )
        }
        launchCamera.launch(mPhotoUri)
    }

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat(YYYYMMDD_HHMMSS, Locale.getDefault()).format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }
}
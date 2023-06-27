package com.example.activitiesandfragments.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.adapter.ImageListAdapter
import com.example.activitiesandfragments.databinding.FragmentImagePickerBinding
import java.io.File

class ImagePickerFragment : Fragment() {

    private lateinit var binding: FragmentImagePickerBinding
    private var uriList: MutableList<Uri> = mutableListOf()
    private val locationForPhotos: Uri by lazy {
        val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val filename = "photo.jpg"
        FileProvider.getUriForFile(requireContext(), "com.your.package.name.fileprovider", File(storageDir, filename))
    }

    companion object {
        var i = 0
    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) {
                Log.d(Constants.Tags.TAG_IMAGE_PICKER_FRAGMENT, "URI: empty")
            } else {
                Log.d(Constants.Tags.TAG_IMAGE_PICKER_FRAGMENT, "URI: $uri")
                uriList.add(uri)
                setUpImageList()
            }
        }

    private val captureImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val thumbnail: Bitmap? = activityResult.data?.getParcelableExtra("data")
                Log.d("IMAGE_PICKER_FRAGMENT", thumbnail.toString())
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        setUpImageList()


        activity?.filesDir?.let { Log.d("IMAGE_PICKER_FRAGMENT", it.absolutePath) }
    }

    private fun setUpOnClickListeners() {
        binding.btnCaptureImage.setOnClickListener {
            captureImage.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(locationForPhotos, "mPhoto$i"))
            })
            i++
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
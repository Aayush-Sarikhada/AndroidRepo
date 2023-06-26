package com.example.activitiesandfragments.adapter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.activitiesandfragments.R


class ImageListAdapter(private val context: Context, private val uriList: List<Uri>) :
    ArrayAdapter<Uri>(context, 0, uriList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentItemView = convertView
        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.row_image_view, parent, false)
        }
        val currentUri: Uri? = getItem(position)
        val imgView = currentItemView!!.findViewById<ImageView>(R.id.imageView)
        imgView.setImageURI(currentUri)

        return currentItemView
    }

}
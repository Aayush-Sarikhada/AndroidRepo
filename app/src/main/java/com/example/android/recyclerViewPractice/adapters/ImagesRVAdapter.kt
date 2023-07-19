package com.example.android.recyclerViewPractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R

class ImagesRVAdapter(
    private val context: Context,
    private val imageIdList: MutableList<Int>,
    private val onDeleteImageButtonClicked: (Int) -> Unit
) : RecyclerView.Adapter<ImagesRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.image)
        val btnDeleteImage: ImageButton = view.findViewById(R.id.img_delete_image)

        init {
            btnDeleteImage.setOnClickListener {
                onDeleteImageButtonClicked(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageIdList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            btnDeleteImage.isGone = position == 0
            imgView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    context.resources,
                    imageIdList[position],
                    null
                )
            )
        }
    }

}
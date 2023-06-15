package com.example.androidpracticeproject.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.models.home.NFTDataModel
import java.nio.file.LinkOption

class NftRVAdapter (
    private val context: Context,
    private val dataList: List<NFTDataModel>
) : RecyclerView.Adapter<NftRVAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgNft: ImageView = view.findViewById(R.id.nft_image)
        val tvNftId: TextView = view.findViewById(R.id.nft_id)
        val tvNftName: TextView = view.findViewById(R.id.nft_name)
        val tvNftPrice: TextView = view.findViewById(R.id.single_nft_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_nft_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("RECYCLERVIEW", dataList.size.toString())
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgNft.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                dataList[position].nftImageId,
                null
            )
        )
        val priceText = dataList[position].nftPrice.toString() + " " + dataList[position].cryptoType.name
        holder.tvNftPrice.text = priceText
        holder.tvNftName.text = dataList[position].nftName
        holder.tvNftId.text = dataList[position].nftId.toString()
    }

}
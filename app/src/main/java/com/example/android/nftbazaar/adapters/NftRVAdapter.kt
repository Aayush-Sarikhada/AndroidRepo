package com.example.android.nftbazaar.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.androidpracticeproject.models.home.NFTDataModel
import com.example.androidpracticeproject.utils.NFTDiffUtilCallback

class NftRVAdapter(
    private val context: Context,
    private var dataList: List<NFTDataModel>
) : RecyclerView.Adapter<NftRVAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgNft: ImageView = view.findViewById(R.id.img_nft)
        val tvNftId: TextView = view.findViewById(R.id.tv_nft_id)
        val tvNftName: TextView = view.findViewById(R.id.tv_nft_name)
        val tvNftPrice: TextView = view.findViewById(R.id.tv_single_nft_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_nft_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("RECYCLER_VIEW", dataList.size.toString())
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
        val priceText =
            dataList[position].nftPrice.toString() + " " + dataList[position].cryptoType.name
        holder.tvNftPrice.text = priceText
        holder.tvNftName.text = dataList[position].nftName
        holder.tvNftId.text = dataList[position].nftId.toString()
    }

    fun submitList(newList: List<NFTDataModel>) {
        val callback = NFTDiffUtilCallback(dataList, newList)
        val diffResult = DiffUtil.calculateDiff(callback)
        dataList = newList
        diffResult.dispatchUpdatesTo(this)
    }

}
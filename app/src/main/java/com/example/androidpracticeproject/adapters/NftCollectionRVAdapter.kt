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
import com.example.androidpracticeproject.models.home.NFTCollectionDataModel
import com.example.androidpracticeproject.utils.ClickListener

class NftCollectionRVAdapter(
    private val context: Context,
    private val dataList: List<NFTCollectionDataModel>,
    private val onClickListener: ClickListener
) : RecyclerView.Adapter<NftCollectionRVAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imgFirstNFT: ImageView = view.findViewById(R.id.first_nft_img)
        val imgSecondNFT: ImageView = view.findViewById(R.id.second_nft_img)
        val imgThirdNFT: ImageView = view.findViewById(R.id.third_nft_img)
        val tvPrice: TextView = view.findViewById(R.id.nft_price)
        val tvNftCollectionName: TextView = view.findViewById(R.id.collection_name)
        val tvCreatorsName: TextView = view.findViewById(R.id.creator_name)
        val btnBuyNow: Button = view.findViewById(R.id.buy_now_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RecyclerViewAdapter","OnCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_nft_collection_card, parent, false)
        val vh = ViewHolder(view)
//        Log.d("RecyclerView", "${vh.adapterPosition}")
        return vh
    }

    override fun getItemCount(): Int {
        Log.d("RecyclerViewAdapter","getItemCount")
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("RecyclerViewAdapter","OnBindViewHolder, position: $position")
        holder.imgFirstNFT.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                dataList[position].nftList[0].nftImageId,
                null
            )
        )
        holder.imgSecondNFT.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                dataList[position].nftList[1].nftImageId,
                null
            )
        )
        holder.imgThirdNFT.setImageDrawable(
            ResourcesCompat.getDrawable(
                context.resources,
                dataList[position].nftList[2].nftImageId,
                null
            )
        )
        val priceText = dataList[position].collectionPrice.toString() + " " + dataList[position].cryptoType.name
        holder.tvPrice.text = priceText
        holder.tvCreatorsName.text = dataList[position].nftCollectionCreatorName
        holder.tvNftCollectionName.text = dataList[position].collectionName
        holder.view.setOnClickListener {
            onClickListener.onClick(position)
        }
        holder.btnBuyNow.setOnClickListener {
            Toast.makeText(context, "Bought the collection!, ID: ${dataList[position].nftCollectionId}",Toast.LENGTH_SHORT).show()
        }
    }

}
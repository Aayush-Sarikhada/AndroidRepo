package com.example.androidpracticeproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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

class NFTAdapter(
    private val context: Context,
    private val dataList: List<NFTCollectionDataModel>,
    private val onClickListener: ClickListener
) : RecyclerView.Adapter<NFTAdapter.ViewHolder>() {

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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nft_collection_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
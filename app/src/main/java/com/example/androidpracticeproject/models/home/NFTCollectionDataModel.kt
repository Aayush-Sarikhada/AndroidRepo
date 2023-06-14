package com.example.androidpracticeproject.models.home

import com.example.androidpracticeproject.utils.CryptoType

data class NFTCollectionDataModel(
    val nftCollectionId: Int,
    val collectionName: String,
    val nftCollectionCreatorName: String,
    val nftList: List<NFTDataModel>,
    val collectionPrice: Double,
    val cryptoType: CryptoType = CryptoType.BTC,
    val ownerName: String
)
package com.example.androidpracticeproject.models.home

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.example.androidpracticeproject.utils.CryptoType
import java.io.Serializable

data class NFTDataModel (
    val nftId: Int = 0,
    val nftImageId: Int = 0,
    val nftName: String = "",
    val nftPrice: Double = 1.0,
    val cryptoType: CryptoType = CryptoType.BTC
)
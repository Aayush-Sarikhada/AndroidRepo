package com.example.androidpracticeproject.models.home

import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.utils.CryptoType

data class NFTCollectionDataModel(
    val nftCollectionId: Int,
    val collectionName: String,
    val nftCollectionCreatorName: String,
    val nftList: List<NFTDataModel>,
    val collectionPrice: Double,
    val cryptoType: CryptoType = CryptoType.BTC,
    val ownerName: String
) {
    companion object {
        fun getNFTCollectionsList(): List<NFTCollectionDataModel> {
            return listOf(

                NFTCollectionDataModel(
                    nftCollectionId = 1001,
                    nftCollectionCreatorName = "John doe",
                    nftList = getNFTDataList(),
                    collectionPrice = 1.0,
                    collectionName = "Futuristic Collection",
                    ownerName = ""
                ),

                NFTCollectionDataModel(
                    nftCollectionId = 1002,
                    nftCollectionCreatorName = "Wolf gupta",
                    nftList = getNFTDataList(),
                    collectionPrice = 0.8,
                    collectionName = "Bumper NFTs",
                    ownerName = ""
                ),

                NFTCollectionDataModel(
                    nftCollectionId = 1003,
                    nftCollectionCreatorName = "Jason ramsey",
                    nftList = getNFTDataList(),
                    collectionPrice = 0.3,
                    collectionName = "NFT Mania ",
                    ownerName = ""
                ),

                NFTCollectionDataModel(
                    nftCollectionId = 1004,
                    nftCollectionCreatorName = "Lisa suman",
                    nftList = getNFTDataList(),
                    collectionPrice = 1.0,
                    collectionName = "Cyber NFTs",
                    ownerName = ""
                ),

                NFTCollectionDataModel(
                    nftCollectionId = 1005,
                    nftCollectionCreatorName = "Jack dorsey",
                    nftList = getNFTDataList(),
                    collectionPrice = 4.0,
                    collectionName = "FigMa NFTs",
                    ownerName = ""
                )

            )
        }

        private fun getNFTDataList(): List<NFTDataModel> {
            return listOf(
                NFTDataModel(
                    nftId = 10011,
                    nftImageId = R.drawable.nft1,
                    nftName = "NFT 1"
                ),
                NFTDataModel(
                    nftId = 10012,
                    nftImageId = R.drawable.nft2,
                    nftName = "NFT 2"
                ),
                NFTDataModel(
                    nftId = 10013,
                    nftImageId = R.drawable.nft3,
                    nftName = "NFT 3"
                ),
                NFTDataModel(
                    nftId = 10014,
                    nftImageId = R.drawable.nft4,
                    nftName = "NFT 4"
                ),
                NFTDataModel(
                    nftId = 10015,
                    nftImageId = R.drawable.nft6,
                    nftName = "NFT 6"
                ),
                NFTDataModel(
                    nftId = 10016,
                    nftImageId = R.drawable.nft7,
                    nftName = "NFT 7"
                ),
                NFTDataModel(
                    nftId = 100117,
                    nftImageId = R.drawable.nft1,
                    nftName = "NFT 1"
                ),
                NFTDataModel(
                    nftId = 10018,
                    nftImageId = R.drawable.nft2,
                    nftName = "NFT 2"
                ),
                NFTDataModel(
                    nftId = 10019,
                    nftImageId = R.drawable.nft3,
                    nftName = "NFT 3"
                ),
                NFTDataModel(
                    nftId = 10020,
                    nftImageId = R.drawable.nft4,
                    nftName = "NFT 4"
                ),
                NFTDataModel(
                    nftId = 10021,
                    nftImageId = R.drawable.nft6,
                    nftName = "NFT 6"
                ),
                NFTDataModel(
                    nftId = 10022,
                    nftImageId = R.drawable.nft7,
                    nftName = "NFT 7"
                )
            ).shuffled()
        }
    }

}
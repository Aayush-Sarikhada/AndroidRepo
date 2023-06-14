package com.example.androidpracticeproject.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.adapters.NFTAdapter
import com.example.androidpracticeproject.databinding.FragmentHomeScreenBinding
import com.example.androidpracticeproject.models.home.NFTCollectionDataModel
import com.example.androidpracticeproject.models.home.NFTDataModel
import com.example.androidpracticeproject.utils.VerticalSpaceItemDecoration

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var nftCollectionRecyclerView: RecyclerView
    private lateinit var nftDataList: List<NFTCollectionDataModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nftDataList = getNFTCollectionsList()
        setupNftCollectionRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    private fun setupNftCollectionRecyclerView() {
        nftCollectionRecyclerView = binding.nftCollectionsRecyclerView
        nftCollectionRecyclerView.layoutManager = LinearLayoutManager(context)
        nftCollectionRecyclerView.addItemDecoration(VerticalSpaceItemDecoration(16))
        nftCollectionRecyclerView.adapter = NFTAdapter(requireContext(), nftDataList) {
            Log.d("MAIN",nftDataList[it].nftList.toString())
            Toast.makeText(requireContext(), nftDataList[it].nftList.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun getNFTCollectionsList(): List<NFTCollectionDataModel> {
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
                nftId = 10016,
                nftImageId = R.drawable.nft6,
                nftName = "NFT 6"
            ),
            NFTDataModel(
                nftId = 10017,
                nftImageId = R.drawable.nft7,
                nftName = "NFT 7"
            )
        ).shuffled()
    }

}
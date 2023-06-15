package com.example.androidpracticeproject.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.androidpracticeproject.Constant
import com.example.androidpracticeproject.adapters.NftRVAdapter
import com.example.androidpracticeproject.databinding.FragmentNftCollectionBinding
import com.example.androidpracticeproject.models.home.NFTCollectionDataModel
import com.example.androidpracticeproject.models.home.NFTDataModel
import com.example.androidpracticeproject.utils.GridSpacingItemDecoration

class NftCollectionFragment : Fragment() {

    private lateinit var binding: FragmentNftCollectionBinding
    private lateinit var nftList: List<NFTDataModel>
    private lateinit var rvNfts: RecyclerView
    private val args: NftCollectionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNfts = binding.nftRecyclerView
        nftList = NFTCollectionDataModel.getNFTCollectionsList()[args.nftIndex].nftList
        rvNfts.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        rvNfts.addItemDecoration(GridSpacingItemDecoration(2,65,true))
        rvNfts.adapter = NftRVAdapter(requireContext(), nftList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNftCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

}
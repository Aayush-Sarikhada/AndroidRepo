package com.example.android.nftbazaar.fragments.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.FragmentNftCollectionBinding
import com.example.android.nftbazaar.Constant
import com.example.android.nftbazaar.adapters.NftRVAdapter
import com.example.android.nftbazaar.home.NFTCollectionDataModel
import com.example.android.nftbazaar.utils.GridSpacingItemDecoration
import com.example.androidpracticeproject.models.home.NFTDataModel


class NftCollectionFragment : Fragment() {

    private lateinit var binding: FragmentNftCollectionBinding
    private lateinit var nftList: List<NFTDataModel>
    private lateinit var rvNfts: RecyclerView
    private lateinit var rvNftAdapter: NftRVAdapter
    private val args: NftCollectionFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRvNfts()
        setUpSearchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNftCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpSearchView() {
        binding.searchView.apply {
            inputType = SearchView.AUTOFILL_TYPE_TEXT
            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val newList = nftList.filter {
                        it.nftName == query
                    }
                    rvNftAdapter.submitList(newList)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })

        }
    }

    private fun setUpRvNfts() {
        rvNfts = binding.nftRecyclerView
        nftList = NFTCollectionDataModel.getNFTCollectionsList()[args.nftIndex].nftList
        rvNftAdapter = NftRVAdapter(requireContext(), nftList)
        rvNfts.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        rvNfts.addItemDecoration(
            GridSpacingItemDecoration(
                Constant.Dimens.NUMBER_OF_COLUMNS,
                Constant.Dimens.GRID_SPACING_NFT,
                true
            )
        )
        rvNfts.adapter = rvNftAdapter
    }

}
package com.example.androidpracticeproject.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.FragmentHomeScreenBinding
import com.example.android.nftbazaar.adapters.NftCollectionRVAdapter
import com.example.android.nftbazaar.utils.VerticalSpaceItemDecoration
import com.example.android.nftbazaar.home.NFTCollectionDataModel

class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var rvNftCollection: RecyclerView
    private lateinit var nftDataList: List<NFTCollectionDataModel>
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nftDataList = NFTCollectionDataModel.getNFTCollectionsList()
        navController = findNavController()
        setupNftCollectionRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    private fun setupNftCollectionRecyclerView() {
        rvNftCollection = binding.nftCollectionsRecyclerView
        rvNftCollection.layoutManager = LinearLayoutManager(context)
        rvNftCollection.addItemDecoration(VerticalSpaceItemDecoration(30))
        rvNftCollection.adapter =
            NftCollectionRVAdapter(requireContext(), nftDataList) { nftListIndex ->
                navController.navigate(
                    HomeScreenFragmentDirections.actionHomeScreenFragmentToNftCollectionFragment(
                        nftListIndex
                    )
                )
            }
    }


}
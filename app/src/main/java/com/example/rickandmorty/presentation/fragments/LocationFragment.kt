package com.example.rickandmorty.presentation.fragments

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.presentation.fragments.adapter.LocationPagAdapter
import com.example.rickandmorty.presentation.fragments.viewModel.LocationViewModel
import com.example.rickandmorty.utils.LoaderStateAdapter
import com.example.rickandmorty.utils.loadListener
import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest


class LocationFragment : BaseFragment<FragmentLocationBinding,LocationViewModel>() {

    private val locationAdapter = LocationPagAdapter()
    override val viewModel: LocationViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentLocationBinding.inflate(inflater)

    override fun initView() {
        super.initView()
        initAdapter()
        getLocation()
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getLocation()
    }

    private fun initAdapter()= with(binding){
        recyclerLocation.adapter = locationAdapter.withLoadStateFooter(
            footer = LoaderStateAdapter()
        )
        loadListener(progressCharactersLoader,recyclerLocation)
    }


    private fun getLocation() {
        safeFlowGather {
            viewModel.location.collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }
}
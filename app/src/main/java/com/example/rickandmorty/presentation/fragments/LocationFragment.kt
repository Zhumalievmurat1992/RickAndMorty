package com.example.rickandmorty.presentation.fragments

import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.presentation.fragments.viewModel.LocationViewModel
import com.example.core.base.BaseFragment
import com.example.rickandmorty.presentation.fragments.adapter.LocationPagAdapter
import com.example.rickandmorty.utils.LoaderStateAdapter
import com.example.rickandmorty.utils.loadListener
import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>() {

    private val locationAdapter = LocationPagAdapter()
    override val viewModel: LocationViewModel by viewModel()
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentLocationBinding.inflate(inflater)

    override fun initView() {
        super.initView()
        initAdapter()
        getLocation()
        searchView()
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
    private fun searchView() = with(binding) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (searchView.query.trim().isNotEmpty() && query!!.length > 2) {
                    getSearchName(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchView.query.trim().isNotEmpty() && newText!!.length > 2) {
                    getSearchName(newText)
                }
                if (searchView.query.trim().isEmpty()) {
                    viewModel.getLocation(name = " ", dimension = " ")

                }
                getLocation()
                return true
            }
        })
    }
    private fun getSearchName(search: String?) {
        viewModel.getLocation(search)
        getLocation()
    }
    private fun getLocation() {
        safeFlowGather {
            viewModel.location.collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }

}
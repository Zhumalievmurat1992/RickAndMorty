package com.example.rickandmorty.presentation.fragments

import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import com.example.core.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.presentation.fragments.adapter.EpisodePagAdapter
import com.example.rickandmorty.presentation.fragments.viewModel.EpisodeViewModel
import com.example.rickandmorty.utils.LoaderStateAdapter
import com.example.rickandmorty.utils.loadListener
import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>() {


    private val episodeAdapter = EpisodePagAdapter()
    override val viewModel: EpisodeViewModel by viewModel()
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentEpisodeBinding.inflate(inflater)

    override fun initView() {
        super.initView()
        initAdapter()
        getEpisodes()
        searchView()
    }
    override fun initViewModel() {
        super.initViewModel()
        viewModel.getEpisodes()
    }
    private fun initAdapter() = with(binding){
        recyclerEpisode.adapter = episodeAdapter.withLoadStateFooter(
            footer = LoaderStateAdapter()
        )
        loadListener(progressCharactersLoader,recyclerEpisode)
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
                    viewModel.getEpisodes(name = " ", episode = " ")

                }
                getEpisodes()
                return true
            }
        })
    }
    private fun getSearchName(search: String?) {
        viewModel.getEpisodes(search)
        getEpisodes()
    }
    private fun getEpisodes() {
        safeFlowGather {
            viewModel.episodes.collectLatest {
                episodeAdapter.submitData(it)
            }

        }
    }




}
package com.example.rickandmorty.presentation.fragments

import android.view.LayoutInflater
import com.example.core.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.presentation.fragments.adapter.EpisodePagAdapter
import com.example.rickandmorty.presentation.fragments.viewModel.EpisodeViewModel
import com.example.rickandmorty.utils.LoaderStateAdapter
import com.example.rickandmorty.utils.loadListener
import com.example.rickandmorty.utils.safeFlowGather
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodeFragment: BaseFragment<FragmentEpisodeBinding,EpisodeViewModel>() {

    private val episodeAdapter = EpisodePagAdapter()
    override val viewModel: EpisodeViewModel by viewModel()


    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentEpisodeBinding.inflate(inflater)

    override fun initView() {
        super.initView()
        initAdapter()
        getEpisodes()
       // searchView()
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

    private fun getEpisodes() {
        safeFlowGather {
            viewModel.episodes.collectLatest {
                episodeAdapter.submitData(it)
            }

        }
    }


    private fun searchView() {
        TODO("Not yet implemented")
    }



}
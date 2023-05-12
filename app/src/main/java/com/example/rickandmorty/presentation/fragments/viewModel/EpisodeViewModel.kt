package com.example.rickandmorty.presentation.fragments.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.rickandmorty.domain.entity.episode.ResultEntity
import com.example.rickandmorty.domain.usecase.EpisodeUseCase
import kotlinx.coroutines.flow.Flow

class EpisodeViewModel(private val episodeUseCase: EpisodeUseCase) : BaseViewModel() {

    private lateinit var _episodes: Flow<PagingData<ResultEntity>>
    val episodes: Flow<PagingData<ResultEntity>>
        get() = _episodes

    fun getEpisodes(
        name: String? = null,
        episode: String? = null,
    ) = launchPagingAsync({
        episodeUseCase(name, episode).cachedIn(viewModelScope)
    }, {
        _episodes = it

    })
}
package com.example.rickandmorty.presentation.fragments.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.rickandmorty.domain.entity.character.Result
import com.example.rickandmorty.domain.entity.episode.ResultEntity
import com.example.rickandmorty.domain.entity.locations.LocResultEntity
import com.example.rickandmorty.domain.usecase.CharacterUseCase
import com.example.rickandmorty.domain.usecase.EpisodeUseCase
import com.example.rickandmorty.domain.usecase.LocationUseCase
import kotlinx.coroutines.flow.Flow

class FirstViewModel(
    private val characterUseCase: CharacterUseCase,
    private val episodeUseCase: EpisodeUseCase,
    private val locationUseCase: LocationUseCase
) : BaseViewModel() {

    private lateinit var _characters: Flow<PagingData<Result>>
//    val characters: Flow<PagingData<Result>>
//        get() = _characters

    private lateinit var _episodes: Flow<PagingData<ResultEntity>>
    //val episodes: Flow<PagingData<ResultEntity>>
       // get() = _episodes

    private lateinit var _location: Flow<PagingData<LocResultEntity>>
//    val location: Flow<PagingData<LocResultEntity>>
//        get() = _location

    fun getCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null
    ) = launchPagingAsync({
        characterUseCase(name, status, species, gender).cachedIn(viewModelScope)
    }, {
        _characters = it
    })

    fun getEpisodes(
        name: String? = null,
        episode: String? = null
    ) = launchPagingAsync({
        episodeUseCase(name, episode).cachedIn(viewModelScope)
    }, {
        _episodes = it

    })

    fun getLocation(
        name: String? = null,
        dimension: String? = null
    ) = launchPagingAsync({
        locationUseCase(name, dimension).cachedIn(viewModelScope)
    }, {
        _location = it

    })

}
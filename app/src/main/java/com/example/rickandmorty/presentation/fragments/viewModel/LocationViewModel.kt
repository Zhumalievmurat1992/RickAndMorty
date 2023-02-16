package com.example.rickandmorty.presentation.fragments.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.rickandmorty.domain.entity.locations.LocResultEntity
import com.example.rickandmorty.domain.usecase.LocationUseCase
import kotlinx.coroutines.flow.Flow

class LocationViewModel(private val locationUseCase: LocationUseCase): BaseViewModel() {

    private lateinit var _location: Flow<PagingData<LocResultEntity>>
    val location: Flow<PagingData<LocResultEntity>>
        get() = _location

    fun getLocation() = launchPagingAsync({
        locationUseCase().cachedIn(viewModelScope)
    }, {
        _location = it

    })
}
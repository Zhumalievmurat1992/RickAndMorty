package com.example.rickandmorty.presentation.fragments.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.usecase.LocationUseCase
import com.example.core.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import com.example.rickandmorty.domain.entity.locations.LocResultEntity

class LocationViewModel(private val locationUseCase: LocationUseCase) : BaseViewModel() {

    private lateinit var _location: Flow<PagingData<LocResultEntity>>
    val location: Flow<PagingData<LocResultEntity>>
        get() = _location

    fun getLocation(
        name: String? = null,
        dimension: String? = null,
    ) = launchPagingAsync({
        locationUseCase(name, dimension).cachedIn(viewModelScope)
    }, {
        _location = it

    })
}
package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.entity.locations.LocResultEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getLocations(): Flow<PagingData<LocResultEntity>>
}
package com.example.rickandmorty.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.paging.LocationsPagingSource
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.repository.LocationRepository

class LocationRepositoryImpl(private val apiService: ApiService): LocationRepository {

    override suspend fun getLocations(name: String?,dimension:String?)= Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 1,
            maxSize = 40,
            initialLoadSize = 20
        ), pagingSourceFactory = { LocationsPagingSource(apiService,name, dimension) }
    ).flow
}
package com.example.rickandmorty.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.paging.EpisodePagingSource
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.repository.EpisodeRepository

class EpisodeRepositoryImpl(private val episodeService: ApiService): EpisodeRepository {
    override suspend fun getEpisode(name: String?,episode:String?) = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 1,
            maxSize = 40,
            initialLoadSize = 20
        ), pagingSourceFactory = { EpisodePagingSource(episodeService,name, episode) }
    ).flow


}
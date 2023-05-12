package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.entity.episode.ResultEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun getEpisode(name: String?,episode: String?): Flow<PagingData<ResultEntity>>
}
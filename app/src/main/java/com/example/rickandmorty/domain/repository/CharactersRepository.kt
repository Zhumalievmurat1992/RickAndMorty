package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.domain.entity.character.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ): Flow<PagingData<Result>>

}
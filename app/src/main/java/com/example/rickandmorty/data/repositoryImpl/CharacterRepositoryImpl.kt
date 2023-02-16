package com.example.rickandmorty.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.paging.CharactersPagingSource
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.repository.CharactersRepository


class CharacterRepositoryImpl(
    private val apiService: ApiService
) : CharactersRepository {

    override suspend fun getAllCharacters(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ) = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 1,
            maxSize = 60,
            initialLoadSize = 20
        ), pagingSourceFactory = { CharactersPagingSource(apiService,name, status, species, gender) }
    ).flow

}
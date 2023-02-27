package com.example.rickandmorty.presentation.fragments.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.rickandmorty.domain.entity.character.Result
import com.example.rickandmorty.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.Flow

class CharacterViewModel(
    private val characterUseCase: CharacterUseCase
) : BaseViewModel() {

    private lateinit var _characters: Flow<PagingData<Result>>
    val characters: Flow<PagingData<Result>>
        get() = _characters

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
}
package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.repository.CharactersRepository

class CharacterUseCase(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(
        name: String?,
        status: String?,
        species: String?,
        gender: String?
    ) =
        charactersRepository.getAllCharacters(name, status, species, gender)

}
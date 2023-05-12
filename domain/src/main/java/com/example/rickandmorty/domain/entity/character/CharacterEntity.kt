package com.example.rickandmorty.domain.entity.character

data class CharacterEntity(
    val info: Info? = null,
    val results: List<Result>? = null
)
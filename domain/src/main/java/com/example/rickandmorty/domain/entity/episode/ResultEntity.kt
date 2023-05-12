package com.example.rickandmorty.domain.entity.episode

data class ResultEntity(
    val airDate: String? = null,
    val characters: List<String>? = null,
    val created: String? = null,
    val episode: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val url: String? = null
)
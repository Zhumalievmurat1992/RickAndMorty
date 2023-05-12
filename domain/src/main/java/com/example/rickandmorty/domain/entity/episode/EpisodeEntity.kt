package com.example.rickandmorty.domain.entity.episode

data class EpisodeEntity(
    val info: InfoEntity? = null,
    val results: List<ResultEntity>? = null
)
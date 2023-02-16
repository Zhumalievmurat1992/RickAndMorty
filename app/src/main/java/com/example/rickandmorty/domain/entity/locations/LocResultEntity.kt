package com.example.rickandmorty.domain.entity.locations

data class LocResultEntity(
    val created: String? = null,
    val dimension: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val residents: List<String>? = null,
    val type: String? = null,
    val url: String? = null
)
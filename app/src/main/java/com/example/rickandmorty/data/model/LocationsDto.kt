package com.example.rickandmorty.data.model

data class LocationsDto(
    val info: LocInfo,
    val results: List<LocResult>
)

data class LocInfo(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)

data class LocResult(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)
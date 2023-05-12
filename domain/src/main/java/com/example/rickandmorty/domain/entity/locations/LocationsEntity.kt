package com.example.rickandmorty.domain.entity.locations

data class LocationsEntity(
    val info: LocInfoEntity? = null,
    val results: List<LocResultEntity>? = null
)
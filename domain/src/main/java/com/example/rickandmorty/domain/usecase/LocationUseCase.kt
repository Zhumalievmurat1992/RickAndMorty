package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.repository.LocationRepository

class LocationUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke(name: String?,dimension: String?) = locationRepository.getLocations(name, dimension)
}
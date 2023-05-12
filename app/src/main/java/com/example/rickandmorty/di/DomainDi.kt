package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecase.CharacterUseCase
import com.example.rickandmorty.domain.usecase.EpisodeUseCase
import com.example.rickandmorty.domain.usecase.LocationUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<CharacterUseCase> { CharacterUseCase(charactersRepository = get()) }
    factory<EpisodeUseCase> { EpisodeUseCase(episodeRepository = get()) }
    factory<LocationUseCase> { LocationUseCase(locationRepository = get()) }
}
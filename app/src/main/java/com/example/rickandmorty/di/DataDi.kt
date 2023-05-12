package com.example.rickandmorty.di

import com.example.rickandmorty.data.repositoryImpl.CharacterRepositoryImpl
import com.example.rickandmorty.data.repositoryImpl.EpisodeRepositoryImpl
import com.example.rickandmorty.data.repositoryImpl.LocationRepositoryImpl
import com.example.rickandmorty.domain.repository.CharactersRepository
import com.example.rickandmorty.domain.repository.EpisodeRepository
import com.example.rickandmorty.domain.repository.LocationRepository
import org.koin.dsl.module

val dataModule = module {
    single<CharactersRepository> { CharacterRepositoryImpl(apiService = get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(episodeService = get()) }
    single<LocationRepository> { LocationRepositoryImpl(apiService = get())  }
}
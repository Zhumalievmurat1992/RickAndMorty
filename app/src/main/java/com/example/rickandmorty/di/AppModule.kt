package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.fragments.viewModel.CharacterViewModel
import com.example.rickandmorty.presentation.fragments.viewModel.EpisodeViewModel
import com.example.rickandmorty.presentation.fragments.viewModel.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    viewModel{ CharacterViewModel(characterUseCase = get()) }
    viewModel{ EpisodeViewModel(episodeUseCase = get()) }
    viewModel{ LocationViewModel(locationUseCase = get()) }
}
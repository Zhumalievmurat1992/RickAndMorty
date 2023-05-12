package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.repository.EpisodeRepository

class EpisodeUseCase(private val episodeRepository: EpisodeRepository) {
    suspend operator fun invoke(name: String?,episode: String?) = episodeRepository.getEpisode(name, episode)
}
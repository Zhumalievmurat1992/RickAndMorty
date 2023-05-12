package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.model.EpInfoDto
import com.example.rickandmorty.data.model.EpResultDto
import com.example.rickandmorty.data.model.EpisodeDto
import com.example.rickandmorty.domain.entity.episode.EpisodeEntity
import com.example.rickandmorty.domain.entity.episode.InfoEntity
import com.example.rickandmorty.domain.entity.episode.ResultEntity
import retrofit2.Response

class MapperForEpisode {

    private fun mapDbModelToEntity(episodeDto: EpisodeDto) =
        EpisodeEntity(
            info = toDbModel(episodeDto.info),
            results = episodeDto.results.map { mapDbModelToEntity(it) }
        )

    private fun toDbModel(info: EpInfoDto) = InfoEntity(
        count = info.count, next = info.next, pages = info.pages, prev = info.prev
    )

    private fun mapDbModelToEntity(result: EpResultDto) = ResultEntity(
        airDate = result.airDate,
        characters = result.characters,
        created = result.created,
        episode = result.episode,
        id = result.id,
        name = result.name,
        url = result.url
    )

    fun mapRespDbModelToRespEntity(response: Response<EpisodeDto?>) = (if (response.isSuccessful) {
        Response.success(response.body()?.let { mapDbModelToEntity(it)})
    } else {
        response.errorBody().let { it?.let { it1 -> Response.error(response.code(), it1) } }
    })!!

}
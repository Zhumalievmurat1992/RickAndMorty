package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.model.*
import com.example.rickandmorty.domain.entity.character.*
import com.example.rickandmorty.domain.entity.character.Info
import com.example.rickandmorty.domain.entity.character.Result
import retrofit2.Response

class MapperForCharacter {

    private fun mapDbModelToEntity(charactersDto: CharactersDto) =
        CharacterEntity(
            info = toDbModel(charactersDto.info),
            results = charactersDto.results.map { mapDbModelToEntity(it) }
        )

    private fun toDbModel(info: InfoDto) = Info(
        count = info.count, next = info.next, pages = info.pages, prev = info.prev
    )

    private fun mapDbModelToEntity(result: ResultDto) =
        Result(
            created = result.created,
            episode = result.episode,
            gender = result.gender,
            id = result.id,
            image = result.image,
            location = toDbModel(result.location),
            name = result.name,
            origin = toDbModel(result.origin),
            species = result.species,
            status = result.status,
            type = result.type,
            url = result.url
        )

    private fun toDbModel(location: LocationDto) = Location(
        name = location.name, url = location.url
    )

    private fun toDbModel(origin: OriginDto) = Origin(
        name = origin.name, url = origin.url
    )

    fun mapRespDbModelToRespEntity(response: Response<CharactersDto?>) = (if (response.isSuccessful) {
        Response.success(response.body()?.let { mapDbModelToEntity(it)})
    } else {
        response.errorBody().let { it?.let { it1 -> Response.error(response.code(), it1) } }
    })!!

}
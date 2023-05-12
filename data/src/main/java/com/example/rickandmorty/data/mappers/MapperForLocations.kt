package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.model.LocInfo
import com.example.rickandmorty.data.model.LocResult
import com.example.rickandmorty.data.model.LocationsDto
import com.example.rickandmorty.domain.entity.locations.LocInfoEntity
import com.example.rickandmorty.domain.entity.locations.LocResultEntity
import com.example.rickandmorty.domain.entity.locations.LocationsEntity
import retrofit2.Response

class MapperForLocations {

    private fun mapDbModelToEntity(locationsDto: LocationsDto) =
        LocationsEntity(
            info = toDbModel(locationsDto.info),
            results = locationsDto.results.map { mapDbModelToEntity(it) }
        )

    private fun toDbModel(info: LocInfo) = LocInfoEntity(
        count = info.count, next = info.next, pages = info.pages, prev = info.prev
    )

    private fun mapDbModelToEntity(result: LocResult) = LocResultEntity(
        created = result.created,
        id = result.id,
        name = result.name,
        url = result.url,
        dimension = result.dimension,
        residents = result.residents,
        type = result.type
    )

    fun mapRespDbModelToRespEntity(response: Response<LocationsDto?>) = (if (response.isSuccessful) {
        Response.success(response.body()?.let { mapDbModelToEntity(it)})
    } else {
        response.errorBody().let { it?.let { it1 -> Response.error(response.code(), it1) } }
    })!!

}
package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.CharactersDto
import com.example.rickandmorty.data.model.EpisodeDto
import com.example.rickandmorty.data.model.LocationsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("gender") gender: String?
    ): Response<CharactersDto?>

    @GET("episode")
    suspend fun getEpisode(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): Response<EpisodeDto?>

    @GET("location")
    suspend fun getLocation(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("dimension") dimension: String?
    ): Response<LocationsDto?>
}


package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("info")
    val info: EpInfoDto,
    @SerializedName("results")
    val results: List<EpResultDto>
)
data class EpInfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class EpResultDto(
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
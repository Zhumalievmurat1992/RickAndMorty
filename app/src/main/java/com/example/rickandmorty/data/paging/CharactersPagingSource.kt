package com.example.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.mappers.MapperForCharacter
import com.example.rickandmorty.data.remote.ApiService
import com.example.rickandmorty.domain.entity.character.Result

class CharactersPagingSource(
    private val apiService: ApiService,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val gender: String?
) :
    PagingSource<Int, Result>() {

    private val map = MapperForCharacter()

    override suspend fun load(params: LoadParams<Int>) = try {
        val position = params.key ?: 1
        val response = map.mapRespDbModelToRespEntity(
            apiService.getAllCharacters(position, name, status, species, gender)
        )
        if (response.body() == null) throw Exception("null")

        if (response.body()!!.results == null) {
            throw Exception("*1*" + response.body())
        }

        val res = response.body()!!.results

        LoadResult.Page(
            data = res.orEmpty(),
            prevKey = if (position == 1) null else position - 1,

            nextKey = if (response.body()?.info != null) position + 1 else null
        )
    } catch (e: Exception) {
        println("+++++++++++" + e.message)
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Result>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
}

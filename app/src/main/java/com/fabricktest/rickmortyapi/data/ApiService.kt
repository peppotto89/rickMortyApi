package com.fabricktest.rickmortyapi.data

import com.fabricktest.rickmortyapi.data.model.Episode
import com.fabricktest.rickmortyapi.data.model.Location
import com.fabricktest.rickmortyapi.data.model.Person
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): Person

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") locationId: Int): Location

    @GET("episode/{ids}")
    suspend fun getEpisodesName(@Path("ids") ids: List<Int>): List<Episode>
}
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
data class CharacterResponse(
    val info: Info,
    val results: List<Person>
)
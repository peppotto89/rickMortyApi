package com.fabricktest.rickmortyapi.data.repository

import com.fabricktest.rickmortyapi.data.CharacterResponse
import com.fabricktest.rickmortyapi.data.model.Location
import com.fabricktest.rickmortyapi.data.model.Person

interface CharacterRepository{
    suspend fun getCharacters(page: Int): CharacterResponse
    suspend fun getCharacterDetail(id: Int): Person
    suspend fun getLocationById(locationId: String): Location
    suspend fun getEpisodes(ids: List<Int>): List<String>
}
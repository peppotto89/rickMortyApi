package com.fabricktest.rickmortyapi.data.repository

import com.fabricktest.rickmortyapi.data.ApiService
import com.fabricktest.rickmortyapi.data.CharacterResponse
import com.fabricktest.rickmortyapi.data.model.Location
import com.fabricktest.rickmortyapi.data.model.Person
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val apiService: ApiService): CharacterRepository {

    override suspend fun getCharacters(page: Int): CharacterResponse {
        try {
            val response = apiService.getCharacters(page)
            if (response.results.isNotEmpty()) {
                return response
            } else {
                throw Exception("Failed to fetch characters")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getCharacterDetail(id: Int): Person {
        try {
            return apiService.getCharacterById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getLocationById(locationId: String): Location {
        return try {
            if(locationId.isNotEmpty()){
                apiService.getLocationById(locationId.toInt())
            }else{
                Location()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getEpisodes(ids: List<Int>): List<String>{
        try {
            return apiService.getEpisodesName(ids).map { it.name }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
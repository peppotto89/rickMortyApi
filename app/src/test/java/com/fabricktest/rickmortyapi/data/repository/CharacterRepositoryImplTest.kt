package com.fabricktest.rickmortyapi.data.repository

import com.fabricktest.rickmortyapi.data.ApiService
import com.fabricktest.rickmortyapi.data.CharacterResponse
import com.fabricktest.rickmortyapi.data.Info
import com.fabricktest.rickmortyapi.data.model.Episode
import com.fabricktest.rickmortyapi.data.model.Location
import com.fabricktest.rickmortyapi.data.model.Person
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(ApiService::class)

class CharacterRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        apiService = PowerMockito.mock(ApiService::class.java)
        repository = CharacterRepositoryImpl(apiService)
    }

    @Test
    fun test_getCharacters() = runBlocking {

        val mockResponse = CharacterResponse(Info(1,1,null,null), listOf(Person(1,"name","species","status","",null, null, listOf())))
        `when`(apiService.getCharacters(1)).thenReturn(mockResponse)

        val result = repository.getCharacters(1)

        assertEquals(mockResponse, result)
    }

    @Test
    fun test_getCharacter() = runBlocking {

        val mockPerson = Person(1,"name","species","status","",null, null, listOf())
        `when`(apiService.getCharacterById(1)).thenReturn(mockPerson)

        val result = repository.getCharacterDetail(1)

        assertEquals(mockPerson, result)
    }

    @Test
    fun test_getLocation() = runBlocking {

        val mockLocation = Location(1, "name")
        `when`(apiService.getLocationById(1)).thenReturn(mockLocation)

        val result = repository.getLocationById("1")

        assertEquals(mockLocation, result)
    }

    @Test
    fun test_getEpisode() = runBlocking {

        val mockEpisode = listOf(Episode(1,"ep1","","", listOf(),"", ""))
        `when`(apiService.getEpisodesName(listOf(1))).thenReturn(mockEpisode)

        val result = repository.getEpisodes(listOf(1))

        assertEquals(listOf("ep1"), result)
    }


}
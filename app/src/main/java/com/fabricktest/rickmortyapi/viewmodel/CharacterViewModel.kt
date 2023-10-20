package com.fabricktest.rickmortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabricktest.rickmortyapi.extra.Constants
import com.fabricktest.rickmortyapi.view.States
import com.fabricktest.rickmortyapi.data.Info
import com.fabricktest.rickmortyapi.extra.getQueryParams
import com.fabricktest.rickmortyapi.data.model.Location
import com.fabricktest.rickmortyapi.data.model.Person
import com.fabricktest.rickmortyapi.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository): ViewModel() {

    private val _state = MutableStateFlow<States>(States.List)
    val state: StateFlow<States> = _state

    private val _characters = MutableStateFlow<List<Person>>(emptyList())
    val characters: StateFlow<List<Person>> = _characters

    private val _infoPage = MutableStateFlow<Info?>(null)
    val infoPage: StateFlow<Info?> = _infoPage

    private val _characterDetail = MutableStateFlow<Person?>(null)
    val characterDetail: StateFlow<Person?> = _characterDetail

    private val _locationDetail = MutableStateFlow<Location?>(null)
    val locationDetail: StateFlow<Location?> = _locationDetail

    private val _episodesList = MutableStateFlow<List<String>>(emptyList())
    val episodesList: StateFlow<List<String>> = _episodesList

    init {
        fetchCharacters(1)
    }

    fun backToList(){
        _state.value = States.List
    }

    fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            val response = characterRepository.getCharacters(page)
            _characters.value = characters.value + response.results
            _infoPage.value = response.info
            _state.value = States.List
        }
    }

    fun getMoreCharacters(){
        val nextPage = infoPage.value?.next
        if(!nextPage.isNullOrEmpty()){
            val queryParams = getQueryParams(nextPage)
            val pageParam = queryParams["page"]
            pageParam?.toIntOrNull()?.let{
                fetchCharacters(it)
            }
        }
    }

    fun getDetails(id: Int, location: String, list: List<String>){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val details = async {
                    characterRepository.getCharacterDetail(id)
                }
                val locationDetails = async {
                    characterRepository.getLocationById(location.replace(Constants.BASE_URL_LOCATION, ""))
                }
                val listEpisodes = list.map{
                    it.replace(Constants.BASE_URL_EPISODE, "").toInt()
                }
                val episodes = async {
                    characterRepository.getEpisodes(listEpisodes)
                }

                val detailsDeferred = details.await()
                val locationDeferred = locationDetails.await()
                val episodesDeferred = episodes.await()

                _characterDetail.value = detailsDeferred
                _locationDetail.value = locationDeferred
                _episodesList.value = episodesDeferred
                _state.value = States.Detail
            }
        } catch (e: Exception) {
            _state.value = States.Error
        }
    }

}
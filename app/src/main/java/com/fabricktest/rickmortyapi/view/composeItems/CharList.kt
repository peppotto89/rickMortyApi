package com.fabricktest.rickmortyapi.view.composeItems

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fabricktest.rickmortyapi.data.model.Person
import com.fabricktest.rickmortyapi.extra.default
import com.fabricktest.rickmortyapi.viewmodel.CharacterViewModel

@Composable
fun CharList(characters: List<Person>, lastIndex: Int, viewModel: CharacterViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Rick & Morty Characters")
                    }
                })
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(characters) { index, character ->
                if(index == lastIndex){
                    LaunchedEffect(Unit){
                        viewModel.getMoreCharacters()
                    }
                }
                CharacterItem(
                    character = character
                ) {

                    viewModel.getDetails(character.id, character.location?.url.default(), character.episode)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
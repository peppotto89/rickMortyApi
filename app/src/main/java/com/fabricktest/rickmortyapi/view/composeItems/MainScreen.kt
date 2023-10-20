package com.fabricktest.rickmortyapi.view.composeItems

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.fabricktest.rickmortyapi.view.States
import com.fabricktest.rickmortyapi.viewmodel.CharacterViewModel

@Composable
fun MainScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val characters by viewModel.characters.collectAsState()
    val state by viewModel.state.collectAsState()
    val lastIndex = characters.lastIndex
    val context = LocalContext.current
    when(state){
        is States.List -> {
            CharList(characters, lastIndex)
        }
        is States.Detail -> {
            CharacterDetail()
        }
        else -> {
            Toast.makeText(context, "Error getting details, retry...", Toast.LENGTH_LONG).show()
        }
    }

}


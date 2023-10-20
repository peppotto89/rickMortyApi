package com.fabricktest.rickmortyapi.view

sealed class States() {
    object List: States()
    object Detail: States()
    object Error: States()
}
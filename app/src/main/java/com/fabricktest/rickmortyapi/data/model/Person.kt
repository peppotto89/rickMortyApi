package com.fabricktest.rickmortyapi.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val image: String,
    val origin: Origin?,
    val location: Location?,
    val episode: List<String>
): Parcelable
package com.fabricktest.rickmortyapi.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val id: Int? = null,
    val name: String = "n/d",
    val url: String? = null,
    val dimension: String = "n/d",
    val residents: List<String> = listOf()
): Parcelable
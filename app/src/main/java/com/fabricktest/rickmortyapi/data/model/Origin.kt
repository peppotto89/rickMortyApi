package com.fabricktest.rickmortyapi.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    val name: String,
    val dimension: String,
    val residentCount: Int
): Parcelable
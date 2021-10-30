package com.dariel25.android.pokeapp.data.network.pokelist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSimpleDto(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val legendary: Boolean = false
) : Parcelable

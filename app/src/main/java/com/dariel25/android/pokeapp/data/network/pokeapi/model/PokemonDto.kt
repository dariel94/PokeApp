package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDto(
    val id: String = "",
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val abilities: List<AbilitySlot>
) : Parcelable

package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpeciesDto(
    val id: String = "",
    val name: String = "",
    val color: Resource,
    @SerializedName("is_legendary") val isLegendary: Boolean = false,
    val generation: Resource,
    @SerializedName("evolution_chain") val evolutionChain: Resource
) : Parcelable

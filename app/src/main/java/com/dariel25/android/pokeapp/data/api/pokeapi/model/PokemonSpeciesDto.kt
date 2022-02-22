package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class PokemonSpeciesDto(
    val id: String = "",
    val name: String = "",
    val color: Resource,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("is_legendary") val isLegendary: Boolean = false,
    val generation: Resource,
    @SerializedName("evolution_chain") val evolutionChain: Resource
) : Parcelable

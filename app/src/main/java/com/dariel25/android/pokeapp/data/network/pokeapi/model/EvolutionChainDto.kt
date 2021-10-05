package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvolutionChainDto(
    val chain: ChainDto
) : Parcelable

@Parcelize
data class ChainDto(
    val species: Resource,
    @SerializedName("evolves_to") val evolvesTo: List<ChainDto>
) : Parcelable

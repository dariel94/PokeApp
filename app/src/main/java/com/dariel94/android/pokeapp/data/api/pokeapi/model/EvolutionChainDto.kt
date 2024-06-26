package com.dariel94.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class EvolutionChainDto(
    val chain: ChainDto
) : Parcelable

@Parcelize
data class ChainDto(
    val species: Resource,
    @SerializedName("evolves_to") val evolvesTo: List<ChainDto>,
    @SerializedName("evolution_details") val evolutionDetailsDto: List<EvolutionDetailDto>
) : Parcelable

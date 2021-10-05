package com.dariel25.android.pokeapp.data.network.pokeapi.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvolutionChainDto(
    val chain: ChainDto
)

@Parcelize
data class ChainDto(
    val species: Resource,
    val evolvesTo: List<ChainDto>
)

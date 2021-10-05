package com.dariel25.android.pokeapp.data.network.pokeapi.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSpeciesDto(
    val id: String = "",
    val name: String = "",
    val color: String = "",
    val isLegendary: Boolean = false,
    val generation: Resource,
    val evolutionChain: EvolutionChainDto
)

package com.dariel25.android.pokeapp.domain.model

data class EvolutionChain(
    val id: String = "",
    val name: String = "",
    val evolvesTo: List<EvolutionChain>
)
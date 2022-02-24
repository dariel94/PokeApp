package com.dariel25.android.pokeapp.domain.model

/**
 * Created by dariel94 on 31/10/2021.
 */
data class EvolutionChain(
    val id: String = "",
    val name: String = "",
    val evolutionDetail: EvolutionDetail?,
    val evolvesTo: List<EvolutionChain>
)

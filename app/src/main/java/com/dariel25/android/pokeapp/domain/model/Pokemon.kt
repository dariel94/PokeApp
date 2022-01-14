package com.dariel25.android.pokeapp.domain.model

/**
 * Created by dariel94 on 30/10/2021.
 */
data class Pokemon(
    val id: String = "",
    val name: String = "",
    val desc: String = "",
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    val evolutions: EvolutionChain
)
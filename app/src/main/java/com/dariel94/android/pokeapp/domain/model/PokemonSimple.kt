package com.dariel94.android.pokeapp.domain.model

/**
 * Created by dariel94 on 31/10/2021.
 */
data class PokemonSimple(
    val id: String,
    val name: String,
    val type1: String,
    val type2: String,
    val generation: Int,
    val legendary: Boolean = false,
)

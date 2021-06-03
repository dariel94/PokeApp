package com.dariel25.android.pokeapp.domain.model

data class PokemonSimple(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val color: String = "",
    val legendary: Boolean = false,
)

package com.dariel25.android.pokeapp.domain.model

data class SimplePokemon(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val color: Int,
    val type1: String = "",
    val type2: String = "",
    val legendary: Boolean = false
)

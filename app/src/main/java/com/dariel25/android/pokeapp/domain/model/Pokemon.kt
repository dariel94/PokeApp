package com.dariel25.android.pokeapp.domain.model

data class Pokemon(
    val id: String = "",
    val name: String = "",
    val color: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val generation: String,
    val legendary: Boolean = false,
    val types: List<String>,
    val abilities: List<String>,
    val evolutionChain: List<String>
)

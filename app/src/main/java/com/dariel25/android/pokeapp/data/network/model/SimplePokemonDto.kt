package com.dariel25.android.pokeapp.data.network.model

data class SimplePokemonDto(
    val id: String = "",
    val name: String = "",
    val types: List<TypeSlot>? = null
)

data class TypeSlot(
    val type: Resource
)


package com.dariel25.android.pokeapp.presentation.model

import androidx.annotation.ColorRes

data class PokemonSimpleUI(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val imageUrl: String = "",
    @ColorRes val cardColor: Int = 0
)

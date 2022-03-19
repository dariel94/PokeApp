package com.dariel25.android.pokeapp.presentation.model

import androidx.annotation.ColorRes

/**
 * Created by dariel94 on 31/10/2021.
 */
data class PokemonSimpleUI(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val imageUrl: String = "",
    val generation: String = "",
    @ColorRes val cardColor: Int = 0
)

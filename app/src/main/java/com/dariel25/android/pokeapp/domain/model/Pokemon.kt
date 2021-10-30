package com.dariel25.android.pokeapp.domain.model

import androidx.annotation.ColorRes

/**
 * Created by dariel94 on 30/10/2021.
 */
data class Pokemon(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val height: String,
    val weight: String,
    @ColorRes val color: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>
)
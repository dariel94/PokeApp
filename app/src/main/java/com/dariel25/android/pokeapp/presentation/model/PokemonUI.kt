package com.dariel25.android.pokeapp.presentation.model

import androidx.annotation.ColorRes
import com.dariel25.android.pokeapp.domain.model.Stat

/**
 * Created by dariel94 on 30/10/2021.
 */
data class PokemonUI(
    val id: String = "",
    val name: String = "",
    val specie: String = "",
    val desc: String = "",
    val height: String = "",
    val weight: String = "",
    val imageUrl: String = "",
    @ColorRes val color: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    val evolutions: List<Evolution>,
    val isLegendary: Boolean,
    val isBaby: Boolean,
    val isMythical: Boolean,
    val baseExperience: String = "",
    val eggGroups: String = "",
    val growthRate: String = "",
    val genderRate: String = "",
    val captureRate: String = "",
    val baseHappiness: String = "",
    val hatchCounter: String = "",
    val generation: String = "",
    val habitat: String = "",
    var isFavorite: Boolean
)

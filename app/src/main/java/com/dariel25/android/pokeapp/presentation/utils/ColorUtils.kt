package com.dariel25.android.pokeapp.presentation.utils

import android.content.Context
import com.dariel25.android.pokeapp.R
import java.util.*

/**
 * Created by David on 5/10/2021.
 */
object ColorUtils {
    fun getPokemonTypeColor(context: Context, type: String): Int {
        val c = when (type.lowercase(Locale.getDefault())) {
            "grass" -> R.color.grass
            "fire" -> R.color.fire
            "water" -> R.color.water
            "bug" -> R.color.bug
            "poison" -> R.color.poison
            "electric" -> R.color.electric
            "ground" -> R.color.ground
            "fairy" -> R.color.fairy
            "fighting" -> R.color.fighting
            "psychic" -> R.color.psychic
            "rock" -> R.color.rock
            "ghost" -> R.color.ghost
            "flying" -> R.color.flying
            "ice" -> R.color.ice
            "dragon" -> R.color.dragon
            "dark" -> R.color.dark
            else -> R.color.other
        }
        return context.resources.getColor(c)
    }
}
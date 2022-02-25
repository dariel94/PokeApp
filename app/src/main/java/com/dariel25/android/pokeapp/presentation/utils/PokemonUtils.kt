package com.dariel25.android.pokeapp.presentation.utils

import com.dariel25.android.pokeapp.R
import java.util.*

/**
 * Created by dariel94 on 31/10/2021.
 */
object PokemonUtils {

    fun getImageUrl(id: String?): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    }

    fun getStatName(statId: String): String {
        return when(statId) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> statId
        }
    }

    fun getFormattedWeight(weight: Int): String {
        val kg = weight / 10f
        val lbs = kg * 2.2F
        return "$kg kg"
    }

    fun getFormattedHeight(height: Int): String {
        val m = height / 10f
        val f = m * 3.28F
        return "$m m"
    }

    fun getIdTitle(id: String): String {
        var idTitle = "#"
        val zeros = 3 - id.length
        repeat (zeros) {
            idTitle += "0"
        }
        return idTitle + id
    }

    fun getPokemonTypeColor(type: String?): Int {
        return when (type?.lowercase(Locale.getDefault())) {
            "grass" -> R.color.pokeapp_grass
            "fire" -> R.color.pokeapp_fire
            "water" -> R.color.pokeapp_water
            "bug" -> R.color.pokeapp_bug
            "poison" -> R.color.pokeapp_poison
            "electric" -> R.color.pokeapp_electric
            "ground" -> R.color.pokeapp_ground
            "fairy" -> R.color.pokeapp_fairy
            "fighting" -> R.color.pokeapp_fighting
            "psychic" -> R.color.pokeapp_psychic
            "rock" -> R.color.pokeapp_rock
            "ghost" -> R.color.pokeapp_ghost
            "flying" -> R.color.pokeapp_flying
            "ice" -> R.color.pokeapp_ice
            "dragon" -> R.color.pokeapp_dragon
            "dark" -> R.color.pokeapp_dark
            else -> R.color.pokeapp_other
        }
    }
}
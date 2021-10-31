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
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Sp Atk"
            "special-defense" -> "Sp Def"
            "speed" -> "Speed"
            else -> statId
        }
    }

    fun getWeightInKilograms(weight: Int): String {
        val kg = weight / 10f
        return "$kg KG"
    }

    fun getHeightInMeters(height: Int): String {
        val m = height / 10f
        return "$m M"
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
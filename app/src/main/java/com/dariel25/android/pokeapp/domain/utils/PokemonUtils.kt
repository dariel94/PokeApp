package com.dariel25.android.pokeapp.domain.utils

import com.dariel25.android.pokeapp.R
import java.util.*

object PokemonUtils {

    fun getImageUrl(id: String): String {
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

    fun getPokemonTypeColor(type: String): Int {
        return when (type.lowercase(Locale.getDefault())) {
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
    }
}
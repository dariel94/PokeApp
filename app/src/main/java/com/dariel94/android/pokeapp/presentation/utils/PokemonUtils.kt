package com.dariel94.android.pokeapp.presentation.utils

import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.presentation.utils.StringUtils.SPANISH
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

    fun getTypeTranslation(type: String, lan: String): String {
        return when (lan) {
            "es" -> {
                 when (type.lowercase()) {
                    "fire" -> "Fuego"
                    "water" -> "Agua"
                    "grass" -> "Planta"
                    "bug" -> "Bicho"
                    "poison" -> "Veneno"
                    "flying" -> "Volador"
                    "electric" -> "Eléctrico"
                    "ground" -> "Tierra"
                    "fairy" -> "Hada"
                    "fighting" -> "Lucha"
                    "ice" -> "Hielo"
                    "rock" -> "Roca"
                    "ghost" -> "Fantasma"
                    "dark" -> "Siniestro"
                    "steel" -> "Acero"
                    "dragon" -> "Dragón"
                    "psychic" -> "Psíquico"
                    "shadow" -> "Sombra"
                    "unknown" -> "Desconocído"
                    else -> type.capitalizeFirst()
                }
            }
            else -> type.capitalizeFirst()
        }
    }

    fun getTriggerDescription(trigger: String, lan: String): String {
        return when (lan) {
            "es" -> {
                 when (trigger) {
                    "level-up" -> "Subir nivel"
                    "trade" -> "Intercambio"
                    "use-item" -> "Usar objeto"
                    "shed" -> "Mudar"
                    "spin" -> "Girar"
                    "tower-of-darkness" -> "Entrenar en la torre de la oscuridad"
                    "tower-of-waters" -> "Entrenar en la torre de las aguas"
                    "three-critical-hits" -> "Asestar tres golpes críticos en una batalla"
                    "take-damage" -> "Ir a algún lugar luego de recibir daño"
                    "other" -> "Otro"
                    "agile-style-move" -> "Movimiento de estilo ágil"
                    "strong-style-move" -> "Movimiento de estilo fuerte"
                    "recoil-damage" -> "Daño por retroceso"
                    else -> trigger
                }
            }
            else -> {
                 when (trigger) {
                    "level-up" -> "Level up"
                    "trade" -> "Trade"
                    "use-item" -> "Use item"
                    "shed" -> "Shed"
                    "spin" -> "Spin"
                    "tower-of-darkness" -> "Train in the Tower of Darkness"
                    "tower-of-waters" -> "Train in the Tower of Waters"
                    "three-critical-hits" -> "Land three critical hits in a battle"
                    "take-damage" -> "Go somewhere after taking damage"
                    "other" -> "Other"
                    "agile-style-move" -> "Agile style move"
                    "strong-style-move" -> "Strong style move"
                    "recoil-damage" -> "Recoil damage"
                    else -> trigger
                }
            }
        }
    }

    fun getGrowthRateTranslation(growthRate: String, lan: String): String {
        return when (lan) {
            "es" -> {
                when (growthRate.lowercase()) {
                    "slow" -> "Lento"
                    "medium" -> "Medio"
                    "fast" -> "Rápido"
                    "medium-slow" -> "Medio-Lento"
                    "fast-then-very-slow" -> "Rápido y luego lento"
                    "slow-then-very-fast" -> "Lento y luego rápido"
                    else -> growthRate.capitalizeFirst()
                }
            }
            else -> growthRate.capitalizeFirst()
        }
    }

    fun getTimeOfDayTranslation(timeOfDay: String, lan: String): String {
        if (lan == SPANISH) {
            return when (timeOfDay) {
                "night" -> "noche"
                else -> "dia"
            }
        }
        return timeOfDay
    }

    fun getCategoryTranslation(cat: String, lan: String): String {
        if (lan == SPANISH) {
            return when (cat) {
                "legendary" -> "Legendario"
                "favorite" -> "Favorito"
                else -> cat.capitalizeFirst()
            }
        }
        return cat.capitalizeFirst()
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

package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDto(
    val id: String = "",
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val abilities: List<AbilitySlot>
) : Parcelable

fun PokemonDto.mapToDomain(): Pokemon {
    val types = types.map {
        it.type.name
    }

    val abilities = abilities.map {
        it.ability.name
    }

    val stats = stats.map {
        Stat(it.stat.name, it.baseStat)
    }

    return Pokemon(
        id,
        name,
        height,
        weight,
        types,
        stats,
        abilities)
}

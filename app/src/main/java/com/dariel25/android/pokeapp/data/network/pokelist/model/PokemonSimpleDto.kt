package com.dariel25.android.pokeapp.data.network.pokelist.model

import android.os.Parcelable
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonSimpleDto(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val legendary: Boolean = false
) : Parcelable

fun PokemonSimpleDto.mapToPokemonSimple(): PokemonSimple {
    return PokemonSimple(
        id,
        name,
        type1,
        type2
    )
}

fun PokemonSimpleDto.mapToPokemonSimpleEntity(): PokemonSimpleEntity {
    return PokemonSimpleEntity(
        id,
        name,
        type1,
        type2,
        legendary
    )
}

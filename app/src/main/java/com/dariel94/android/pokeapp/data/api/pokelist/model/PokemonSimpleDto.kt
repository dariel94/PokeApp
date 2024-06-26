package com.dariel94.android.pokeapp.data.api.pokelist.model

import android.os.Parcelable
import com.dariel94.android.pokeapp.data.database.model.PokemonSimpleEntity
import com.dariel94.android.pokeapp.domain.model.PokemonSimple
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class PokemonSimpleDto(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val generation: Float = 0F,
    val legendary: Boolean = false
) : Parcelable

fun PokemonSimpleDto.mapToDomain(): PokemonSimple {
    return PokemonSimple(
        id,
        name,
        type1,
        type2,
        generation.toInt(),
        legendary
    )
}

fun PokemonSimpleDto.mapToEntity(): PokemonSimpleEntity {
    return PokemonSimpleEntity(
        id,
        name,
        type1,
        type2,
        generation.toInt(),
        legendary
    )
}

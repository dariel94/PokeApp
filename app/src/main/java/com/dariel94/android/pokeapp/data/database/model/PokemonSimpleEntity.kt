package com.dariel94.android.pokeapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariel94.android.pokeapp.domain.model.PokemonSimple

/**
 * Created by dariel94 on 31/10/2021.
 */
@Entity
data class PokemonSimpleEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "type1") val type1: String = "",
    @ColumnInfo(name = "type2") val type2: String = "",
    @ColumnInfo(name = "generation") val generation: Int = 0,
    @ColumnInfo(name = "legendary") val legendary: Boolean = false
)

fun PokemonSimpleEntity.mapToPokemonSimple(): PokemonSimple {
    return PokemonSimple(
        id,
        name,
        type1,
        type2,
        generation
    )
}

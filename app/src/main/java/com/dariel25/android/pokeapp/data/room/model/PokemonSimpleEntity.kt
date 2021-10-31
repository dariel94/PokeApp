package com.dariel25.android.pokeapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariel25.android.pokeapp.domain.model.PokemonSimple

@Entity
data class PokemonSimpleEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "type1") val type1: String = "",
    @ColumnInfo(name = "type2") val type2: String = "",
    @ColumnInfo(name = "legendary") val legendary: Boolean = false
)

fun PokemonSimpleEntity.mapToPokemonSimple(): PokemonSimple {
    return PokemonSimple(
        id,
        name,
        type1,
        type2
    )
}

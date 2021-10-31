package com.dariel25.android.pokeapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat

@Entity
data class PokemonEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "height") val height: Int = 0,
    @ColumnInfo(name = "weight") val weight: Int = 0,
    @ColumnInfo(name = "types") val types: List<String>,
    @ColumnInfo(name = "stats") val stats: List<Stat>,
    @ColumnInfo(name = "abilities") val abilities: List<String>
)

fun PokemonEntity.mapToDomain(): Pokemon {
    return Pokemon(
        id,
        name,
        height,
        weight,
        types,
        stats,
        abilities)
}

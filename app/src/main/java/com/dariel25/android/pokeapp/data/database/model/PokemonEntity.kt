package com.dariel25.android.pokeapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat

/**
 * Created by dariel94 on 31/10/2021.
 */
@Entity
data class PokemonEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "desc") val desc: String = "",
    @ColumnInfo(name = "height") val height: Int = 0,
    @ColumnInfo(name = "weight") val weight: Int = 0,
    @ColumnInfo(name = "types") val types: List<String>,
    @ColumnInfo(name = "stats") val stats: List<Stat>,
    @ColumnInfo(name = "abilities") val abilities: List<String>,
    @ColumnInfo(name = "evolution_chain") val evolutionChain: EvolutionChain,
    @ColumnInfo(name = "is_legendary") val isLegendary: Boolean,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)

fun PokemonEntity.mapToDomain(): Pokemon {
    return Pokemon(
        id,
        name,
        desc,
        height,
        weight,
        types,
        stats,
        abilities,
        evolutionChain,
        isLegendary,
        isFavorite
    )
}

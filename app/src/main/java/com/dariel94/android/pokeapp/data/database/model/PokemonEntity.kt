package com.dariel94.android.pokeapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariel94.android.pokeapp.domain.model.*

/**
 * Created by dariel94 on 31/10/2021.
 */
@Entity
data class PokemonEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "specie") val specie: String = "",
    @ColumnInfo(name = "desc") val desc: String = "",
    @ColumnInfo(name = "height") val height: Int = 0,
    @ColumnInfo(name = "weight") val weight: Int = 0,
    @ColumnInfo(name = "types") val types: List<String>,
    @ColumnInfo(name = "stats") val stats: List<Stat>,
    @ColumnInfo(name = "abilities") val abilities: List<Ability>,
    @ColumnInfo(name = "evolution_chain") val evolutionChain: EvolutionChain,
    @ColumnInfo(name = "is_legendary") val isLegendary: Boolean,
    @ColumnInfo(name = "is_baby") val isBaby: Boolean,
    @ColumnInfo(name = "is_mythical")  val isMythical: Boolean,
    @ColumnInfo(name = "base_experience") val baseExperience: Int,
    @ColumnInfo(name = "egg_groups") val eggGroups: List<String>,
    @ColumnInfo(name = "growth_rate") val growthRate: String,
    @ColumnInfo(name = "gender_rate") val genderRate: Int,
    @ColumnInfo(name = "capture_rate") val captureRate: Int,
    @ColumnInfo(name = "base_happiness") val baseHappiness: Int,
    @ColumnInfo(name = "hatch_counter") val hatchCounter: Int,
    @ColumnInfo(name = "generation") val generation: String,
    @ColumnInfo(name = "habitat") val habitat: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "varieties") val varieties: List<Variety>
)

fun PokemonEntity.mapToDomain(): Pokemon {
    return Pokemon(
        id,
        name,
        specie,
        desc,
        height,
        weight,
        types,
        stats,
        abilities,
        evolutionChain,
        isLegendary,
        isBaby,
        isMythical,
        baseExperience,
        eggGroups,
        growthRate,
        genderRate,
        captureRate,
        baseHappiness,
        hatchCounter,
        generation,
        habitat,
        isFavorite,
        varieties
    )
}

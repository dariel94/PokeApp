package com.dariel25.android.pokeapp.domain.model

import com.dariel25.android.pokeapp.data.database.model.PokemonEntity

/**
 * Created by dariel94 on 30/10/2021.
 */
data class Pokemon(
    val id: String,
    val name: String,
    val specie: String,
    val desc: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<Ability>,
    val evolutionChain: EvolutionChain,
    val isLegendary: Boolean,
    val isBaby: Boolean = false,
    val isMythical: Boolean = false,
    val baseExperience: Int,
    val eggGroups: List<String>,
    val growthRate: String,
    val genderRate: Int,
    val captureRate: Int,
    val baseHappiness: Int,
    val hatchCounter: Int,
    val generation: String,
    val habitat: String,
    var isFavorite: Boolean = false
) {
    fun mapToEntity(): PokemonEntity =
        PokemonEntity(
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
            isFavorite
        )
}

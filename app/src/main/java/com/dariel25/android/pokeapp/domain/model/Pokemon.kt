package com.dariel25.android.pokeapp.domain.model

import com.dariel25.android.pokeapp.data.database.model.PokemonEntity

/**
 * Created by dariel94 on 30/10/2021.
 */
data class Pokemon(
    val id: String = "",
    val name: String = "",
    val desc: String = "",
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    val evolutionChain: EvolutionChain,
    val isLegendary: Boolean,
    var isFavorite: Boolean = false
) {
    fun mapToEntity(): PokemonEntity =
        PokemonEntity(
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

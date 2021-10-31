package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.core.BaseMapper
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils

/**
 * Created by dariel94 on 31/10/2021.
 */
object PokemonToUIMapper : BaseMapper<Pokemon, PokemonUI> {
    override fun map(type: Pokemon): PokemonUI {
        val color = if (type.types.isNotEmpty()) {
            PokemonUtils.getPokemonTypeColor(type.types.first())
        } else {
            PokemonUtils.getPokemonTypeColor("")
        }
        val stats = type.stats.map {
            Stat(PokemonUtils.getStatName(it.name), it.value)
        }
        return PokemonUI(
            type.id,
            type.name,
            PokemonUtils.getHeightInMeters(type.height),
            PokemonUtils.getWeightInKilograms(type.weight),
            type.types,
            stats,
            type.abilities,
            PokemonUtils.getImageUrl(type.id),
            color
        )
    }
}
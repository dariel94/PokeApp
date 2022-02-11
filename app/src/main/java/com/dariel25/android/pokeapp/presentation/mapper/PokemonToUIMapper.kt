package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.core.BaseMapper
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.model.Evolution
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

        val abilities = type.abilities.map {
            it.replace("-", "").uppercase()
        }

        val types = type.types.map {
            it.replaceFirstChar { c -> c.uppercase() }
        }

        val evolutions: ArrayList<Evolution> = arrayListOf()
        mapEvolutionChainToList(type.evolutionChain, evolutions)

        return PokemonUI(
            type.id,
            type.name,
            type.desc,
            PokemonUtils.getHeightInMeters(type.height),
            PokemonUtils.getWeightInKilograms(type.weight),
            PokemonUtils.getImageUrl(type.id),
            color,
            types,
            stats,
            abilities,
            evolutions
        )
    }

    private fun mapEvolutionChainToList(
        evolutionChain: EvolutionChain,
        evolutions: ArrayList<Evolution>
    ) {

        for (evolve in evolutionChain.evolvesTo) {
            val evolution = Evolution(
                evolutionChain.id,
                evolutionChain.name,
                evolve.id,
                evolve.name,
                evolve.condition
            )
            evolutions.add(evolution)
            mapEvolutionChainToList(evolve, evolutions)
        }
    }
}
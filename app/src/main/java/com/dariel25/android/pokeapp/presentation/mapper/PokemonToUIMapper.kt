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
    override fun map(obj: Pokemon): PokemonUI {
        val color = if (obj.types.isNotEmpty()) {
            PokemonUtils.getPokemonTypeColor(obj.types.first())
        } else {
            PokemonUtils.getPokemonTypeColor("")
        }
        val stats = obj.stats.map {
            Stat(PokemonUtils.getStatName(it.name), it.value)
        }

        val abilities = obj.abilities.map {
            it.replace("-", "").uppercase()
        }

        val types = obj.types.map {
            it.replaceFirstChar { c -> c.uppercase() }
        }

        val evolutions: ArrayList<Evolution> = arrayListOf()
        mapEvolutionChainToList(obj.evolutionChain, evolutions)

        return PokemonUI(
            obj.id,
            obj.name,
            obj.desc,
            PokemonUtils.getHeightInMeters(obj.height),
            PokemonUtils.getWeightInKilograms(obj.weight),
            PokemonUtils.getImageUrl(obj.id),
            color,
            types,
            stats,
            abilities,
            evolutions,
            obj.isLegendary
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
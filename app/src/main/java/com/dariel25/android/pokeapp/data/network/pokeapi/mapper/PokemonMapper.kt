
package com.dariel25.android.pokeapp.data.network.pokeapi.mapper

import com.dariel25.android.pokeapp.data.network.pokeapi.model.ChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.data.utils.StringUtils
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat

/**
 * Created by dariel94 on 11/1/2022.
 */
object PokemonMapper {

    fun mapToDomain(
        pokemonDto: PokemonDto,
        pokemonSpeciesDto: PokemonSpeciesDto,
        evolutionChainDto: EvolutionChainDto
    ) : Pokemon {
        val types = pokemonDto.types.map {
            it.type.name
        }

        val abilities = pokemonDto.abilities.map {
            it.ability.name
        }

        val stats = pokemonDto.stats.map {
            Stat(it.stat.name, it.baseStat)
        }

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            pokemonSpeciesDto.flavorTextEntries[6].flavorText.replace('\n', ' '),
            pokemonDto.height,
            pokemonDto.weight,
            types,
            stats,
            abilities,
            getEvolutionChain(evolutionChainDto.chain))
    }

    private fun getEvolutionChain(chainDto: ChainDto): EvolutionChain {
        val list = chainDto.evolvesTo.map {
            getEvolutionChain(it)
        }

        var condition = ""
        if (chainDto.evolutionDetails.isNotEmpty()) {
            val level = chainDto.evolutionDetails[0].level
            val happiness = chainDto.evolutionDetails[0].happiness
            val item = chainDto.evolutionDetails[0].item
            val heldItem = chainDto.evolutionDetails[0].heldItem
            val trigger = chainDto.evolutionDetails[0].trigger
            when {
                level != null -> condition = "Lvl $level"
                item != null -> condition = item.name.replace('-', ' ').uppercase()
                heldItem != null -> condition = heldItem.name.replace('-', ' ').uppercase()
                happiness != null -> condition = "Happiness $happiness"
                trigger != null -> condition = trigger.name.replace('-', ' ').uppercase()
            }
        }

        return EvolutionChain(
            StringUtils.getIdFromUrl(chainDto.species.url),
            chainDto.species.name.replaceFirstChar { c -> c.uppercase() },
            condition,
            list)
    }
}

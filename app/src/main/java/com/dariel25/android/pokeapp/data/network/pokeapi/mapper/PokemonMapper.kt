
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

        val evolutionChain = evolutionChainDto.chain.

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            pokemonSpeciesDto.flavorTextEntries[0].flavorText,
            pokemonDto.height,
            pokemonDto.weight,
            types,
            stats,
            abilities,
            evolutionChainDto.chain.)
    }

    private fun getEvolutionChain(chainDto: ChainDto?): EvolutionChain? {
        chainDto?.let {
            return EvolutionChain(
                StringUtils.getIdFromUrl(it.species.url),
                getEvolutionChain(it.evolvesTo))
        }
        return null
    }
}
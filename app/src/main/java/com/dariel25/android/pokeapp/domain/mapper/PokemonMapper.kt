package com.dariel25.android.pokeapp.domain.mapper

import com.dariel25.android.pokeapp.data.network.pokeapi.model.ChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun mapDtoToUI(
        pokemonDto: PokemonDto,
        speciesDto: PokemonSpeciesDto,
        chainDto: EvolutionChainDto
    ): Pokemon {

        val types = pokemonDto.types.map {
            it.type.name
        }

        val abilities = pokemonDto.abilities.map {
            it.ability.name
        }

        val stats = pokemonDto.stats.map {
            Stat(it.stat.name, it.baseStat)
        }

        val evolutionChain = EvolutionChain(
            getIdFromUrl(chainDto.chain.species.url),
            chainDto.chain.species.name,
            getEvolutionChain(chainDto.chain.evolvesTo)
        )

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            speciesDto.color.name,
            pokemonDto.height,
            pokemonDto.weight,
            speciesDto.generation.name,
            speciesDto.isLegendary,
            types,
            stats,
            abilities,
            evolutionChain)
    }

    private fun getEvolutionChain(listDto: List<ChainDto>): List<EvolutionChain> =
        listDto.map {
            EvolutionChain(
                getIdFromUrl(it.species.url),
                it.species.name,
                getEvolutionChain(it.evolvesTo)
            )
        }

    fun getIdFromUrl(url: String): String {
        val splitUrl = url.split('/')
        return splitUrl[splitUrl.size - 2]
    }
}

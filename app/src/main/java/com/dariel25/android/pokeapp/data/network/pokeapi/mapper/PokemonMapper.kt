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

        var desc = ""
        for (entry in pokemonSpeciesDto.flavorTextEntries) {
            if (entry.language.name == "en") {
                desc = entry.flavorText.replace('\n', ' ')
                break
            }
        }

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            desc,
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
            val evolutionDetailDto = chainDto.evolutionDetails[0]
            evolutionDetailDto.trigger?.let { condition += normalize(it.name) + " " }
            evolutionDetailDto.level?.let { condition += it }
            evolutionDetailDto.item?.let { condition += "\n${normalize(it.name)}" }
            evolutionDetailDto.timeOfDay?.let {
                if (it.isNotEmpty()) condition += "at $it"
            }
            evolutionDetailDto.heldItem?.let { condition += "\n Held ${normalize(it.name)}" }
            evolutionDetailDto.location?.let { condition += "\nIn ${normalize(it.name)}" }
            evolutionDetailDto.knownMoveType?.let { condition += "\nKnown ${normalize(it.name)} move" }
            evolutionDetailDto.knownMove?.let { condition += "\nKnown ${normalize(it.name)}" }
            evolutionDetailDto.happiness?.let { condition += "\nHappiness $it" }
            evolutionDetailDto.beauty?.let { condition += "\nBeauty $it" }
            evolutionDetailDto.affection?.let { condition += "\nAffection $it" }
            evolutionDetailDto.gender?.let {
                condition += "\n${if (it == 0) "Male" else "Female"} gender"
            }
            evolutionDetailDto.relativePhysicalStats?.let {
                condition += when (it) {
                    0 -> {
                        "\nIf Attack = Defense"
                    }
                    1 -> {
                        "\nIf Attack > Defense"
                    }
                    else -> {
                        "\nIf Attack < Defense"
                    }
                }
            }
        }

        return EvolutionChain(
            StringUtils.getIdFromUrl(chainDto.species.url),
            chainDto.species.name.replaceFirstChar { c -> c.uppercase() },
            condition,
            list)
    }

    private fun normalize(string: String) =
        string.replace('-', ' ').replaceFirstChar { c -> c.uppercase() }
}

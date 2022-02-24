package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.api.pokeapi.model.ChainDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.data.utils.StringUtils
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.utils.capitalizeWords
import com.dariel25.android.pokeapp.presentation.utils.normalizeProperty

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

        var specie = ""
        for (entry in pokemonSpeciesDto.genera) {
            if (entry.language.name == "en") {
                specie = entry.genus
                break
            }
        }

        val eggGroups = pokemonSpeciesDto.eggGroups.map { it.name }

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            specie,
            desc,
            pokemonDto.height,
            pokemonDto.weight,
            types,
            stats,
            abilities,
            getEvolutionChain(evolutionChainDto.chain),
            pokemonSpeciesDto.isLegendary,
            pokemonSpeciesDto.isBaby,
            pokemonSpeciesDto.isMythical,
            pokemonDto.baseExperience,
            eggGroups,
            safeString(pokemonSpeciesDto.growthRate?.name),
            pokemonSpeciesDto.genderRate,
            pokemonSpeciesDto.captureRate,
            pokemonSpeciesDto.baseHappiness,
            pokemonSpeciesDto.hatchCounter,
            safeString(pokemonSpeciesDto.generation?.name),
            safeString(pokemonSpeciesDto.habitat?.name)
        )
    }

    private fun safeString(string: String?): String {
        return string ?: ""
    }

    private fun getEvolutionChain(chainDto: ChainDto): EvolutionChain {
        val list = chainDto.evolvesTo.map {
            getEvolutionChain(it)
        }

        var condition = ""
        if (chainDto.evolutionDetails.isNotEmpty()) {
            val evolutionDetailDto = chainDto.evolutionDetails[0]
            evolutionDetailDto.trigger?.let { condition += it.name.normalizeProperty() + " " }
            evolutionDetailDto.level?.let { condition += it }
            evolutionDetailDto.item?.let { condition += "\n${it.name.normalizeProperty()}" }
            evolutionDetailDto.timeOfDay?.let {
                if (it.isNotEmpty()) condition += "at $it"
            }
            evolutionDetailDto.heldItem?.let { condition += "\n Held ${it.name.normalizeProperty()}" }
            evolutionDetailDto.location?.let { condition += "\nIn ${it.name.normalizeProperty()}" }
            evolutionDetailDto.knownMoveType?.let { condition += "\nKnown ${it.name.normalizeProperty()} move" }
            evolutionDetailDto.knownMove?.let { condition += "\nKnown ${it.name.normalizeProperty()}" }
            evolutionDetailDto.happiness?.let { condition += "\nHappiness $it" }
            evolutionDetailDto.beauty?.let { condition += "\nBeauty $it" }
            evolutionDetailDto.affection?.let { condition += "\nAffection $it" }
            if (evolutionDetailDto.needsOverWorldRain) { condition += "\nNeeds over world rain" }
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
            chainDto.species.name.capitalizeWords(),
            condition,
            list)
    }
}

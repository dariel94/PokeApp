package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.api.pokeapi.model.ChainDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.api.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.data.utils.StringUtils
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.EvolutionDetail
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.utils.capitalizeWords
import com.dariel25.android.pokeapp.presentation.utils.safe

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
                desc = entry.flavorText
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
            pokemonSpeciesDto.growthRate?.name.safe(),
            pokemonSpeciesDto.genderRate,
            pokemonSpeciesDto.captureRate,
            pokemonSpeciesDto.baseHappiness,
            pokemonSpeciesDto.hatchCounter,
            pokemonSpeciesDto.generation?.name.safe(),
            pokemonSpeciesDto.habitat?.name.safe()
        )
    }

    private fun getEvolutionChain(chainDto: ChainDto): EvolutionChain {
        val list = chainDto.evolvesTo.map {
            getEvolutionChain(it)
        }

        var evolutionDetail: EvolutionDetail? = null
        val detailsDto = chainDto.evolutionDetailsDto

        if (detailsDto.isNotEmpty()) {
            val detailDto = detailsDto[0]
            evolutionDetail = EvolutionDetail(
                detailDto.level,
                detailDto.happiness,
                detailDto.affection,
                detailDto.gender,
                detailDto.relativePhysicalStats,
                detailDto.needsOverWorldRain,
                detailDto.item?.name,
                detailDto.heldItem?.name,
                detailDto.knownMoveType?.name,
                detailDto.knownMove?.name,
                detailDto.timeOfDay,
                detailDto.beauty,
                detailDto.location?.name,
                detailDto.trigger?.name
            )
        }

        return EvolutionChain(
            StringUtils.getIdFromUrl(chainDto.species.url),
            chainDto.species.name.capitalizeWords(),
            evolutionDetail,
            list)
    }
}

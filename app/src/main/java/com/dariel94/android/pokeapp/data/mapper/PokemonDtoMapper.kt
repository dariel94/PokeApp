/*
 * Created by David on 18/2/22 21:46
 */

package com.dariel94.android.pokeapp.data.mapper

import com.dariel94.android.pokeapp.data.api.pokeapi.model.*
import com.dariel94.android.pokeapp.data.utils.StringUtils
import com.dariel94.android.pokeapp.domain.model.*
import com.dariel94.android.pokeapp.presentation.utils.capitalizeWords
import com.dariel94.android.pokeapp.presentation.utils.normalizeProperty
import com.dariel94.android.pokeapp.presentation.utils.safe

fun PokemonDto.mapToDomain(
    pokemonSpeciesDto: PokemonSpeciesDto,
    evolutionChainDto: EvolutionChainDto,
    abilitiesDto: List<AbilityDto>
) : Pokemon {
    val types = this.types.map {
        it.type.name
    }

    val abilities = abilitiesDto.map {
        it.toDomain()
    }

    val stats = this.stats.map {
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

    val varieties = pokemonSpeciesDto.varieties.map {
        Variety(
            StringUtils.getIdFromUrl(it.pokemon.url),
            it.pokemon.name.normalizeProperty(),
            it.isDefault
        )
    }

    return Pokemon(
        this.id,
        this.name,
        specie,
        desc,
        this.height,
        this.weight,
        types,
        stats,
        abilities,
        evolutionChainDto.chain.toEvolutionChain(),
        pokemonSpeciesDto.isLegendary,
        pokemonSpeciesDto.isBaby,
        pokemonSpeciesDto.isMythical,
        this.baseExperience,
        eggGroups,
        pokemonSpeciesDto.growthRate?.name.safe(),
        pokemonSpeciesDto.genderRate,
        pokemonSpeciesDto.captureRate,
        pokemonSpeciesDto.baseHappiness,
        pokemonSpeciesDto.hatchCounter,
        pokemonSpeciesDto.generation?.name.safe(),
        pokemonSpeciesDto.habitat?.name.safe(),
        false,
        varieties
    )
}

private fun ChainDto.toEvolutionChain(): EvolutionChain {
    val list = this.evolvesTo.map {
        it.toEvolutionChain()
    }

    var evolutionDetail: EvolutionDetail? = null
    val detailsDto = this.evolutionDetailsDto

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
        StringUtils.getIdFromUrl(this.species.url),
        this.species.name.capitalizeWords(),
        evolutionDetail,
        list)
}

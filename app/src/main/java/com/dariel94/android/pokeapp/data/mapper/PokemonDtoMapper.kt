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
    abilitiesDto: List<AbilityDto>,
    eggGroupsDto: List<EggGroupDto>,
    lan: String,
) : Pokemon {
    val types = this.types.map {
        it.type.name
    }

    val abilities = abilitiesDto.map {
        it.toDomain(lan)
    }

    val stats = this.stats.map {
        Stat(it.stat.name, it.baseStat)
    }

    var desc = getFlavourText(lan, pokemonSpeciesDto.flavorTextEntries)

    var specie = getSpecieText(lan, pokemonSpeciesDto.genera)

    val eggGroups = eggGroupsDto.map { getEggGroupText(lan, it.names) }

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

private fun getFlavourText(lan: String, entries: List<FlavorTextEntry>): String {
    entries.find {
        it.language.name == lan
    }?.flavorText?.let {
        return it
    }
    entries.find {
        it.language.name == "en"
    }?.flavorText?.let {
        return it
    }
    return entries.getOrNull(0)?.flavorText ?: ""
}

private fun getSpecieText(lan: String, entries: List<GeneraTextEntry>): String {
    entries.find {
        it.language.name == lan
    }?.genus?.let {
        return it
    }
    entries.find {
        it.language.name == "en"
    }?.genus?.let {
        return it
    }
    return entries.getOrNull(0)?.genus ?: ""
}

private fun getEggGroupText(lan: String, entries: List<EggGroupNameDto>?): String {
    entries?.find {
        it.language.name == lan
    }?.name?.let {
        return it
    }
    entries?.find {
        it.language.name == "en"
    }?.name?.let {
        return it
    }
    return entries?.getOrNull(0)?.name ?: ""
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

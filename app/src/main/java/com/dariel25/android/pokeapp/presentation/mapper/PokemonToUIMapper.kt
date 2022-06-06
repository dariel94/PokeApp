package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.model.Evolution
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.utils.capitalizeFirst
import com.dariel25.android.pokeapp.presentation.utils.normalizeProperty

/**
 * Created by dariel94 on 31/10/2021.
 */
fun Pokemon.toUI(): PokemonUI {
    val color = if (this.types.isNotEmpty()) {
        PokemonUtils.getPokemonTypeColor(this.types.first())
    } else {
        PokemonUtils.getPokemonTypeColor("")
    }

    val stats = this.stats.map {
        Stat(PokemonUtils.getStatName(it.name), it.value)
    }

    val types = this.types.map {
        it.capitalizeFirst()
    }

    val evolutions: ArrayList<Evolution> = arrayListOf()
    mapEvolutionChainToList(this.evolutionChain, evolutions)

    val genderRateText = if (this.genderRate != -1) {
        "${(8 - this.genderRate) * 12.5F}% male, ${this.genderRate * 12.5F}% female"
    } else {
        "Genderless"
    }

    val capturePercent = String.format("%.2f", this.captureRate / 255F * 33.33F)
    val captureRateString = "${this.captureRate} ($capturePercent%)"

    val eggGroupsText = this.eggGroups.joinToString(", ") { it.normalizeProperty() }

    val hatchCounterText = "${this.hatchCounter} (${(this.hatchCounter-1) * 257}-${this.hatchCounter * 257} steps)"

    return PokemonUI(
        PokemonUtils.getIdTitle(this.id),
        this.name.normalizeProperty(),
        this.specie.normalizeProperty(),
        this.desc.replace('\n', ' '),
        PokemonUtils.getFormattedHeight(this.height),
        PokemonUtils.getFormattedWeight(this.weight),
        PokemonUtils.getImageUrl(this.id),
        color,
        types,
        stats,
        abilities,
        evolutions,
        this.isLegendary,
        this.isBaby,
        this.isMythical,
        this.baseExperience.toString(),
        eggGroupsText,
        this.growthRate.normalizeProperty(),
        genderRateText,
        captureRateString,
        this.baseHappiness.toString(),
        hatchCounterText,
        this.generation,
        this.habitat,
        this.isFavorite,
        this.varieties.filter { it.id != this.id }
    )
}

private fun mapEvolutionChainToList(
    evolutionChain: EvolutionChain,
    evolutions: ArrayList<Evolution>
) {
    for (evolve in evolutionChain.evolvesTo) {

        var condition = ""
        evolve.evolutionDetail?.let { evolutionDetail ->
            evolutionDetail.trigger?.let { condition += it.normalizeProperty() + " " }
            evolutionDetail.level?.let { condition += it }
            evolutionDetail.item?.let { condition += "\n${it.normalizeProperty()}" }
            evolutionDetail.timeOfDay?.let {
                if (it.isNotEmpty()) condition += "at $it"
            }
            evolutionDetail.heldItem?.let { condition += "\n Held ${it.normalizeProperty()}" }
            evolutionDetail.location?.let { condition += "\nIn ${it.normalizeProperty()}" }
            evolutionDetail.knownMoveType?.let { condition += "\nKnown ${it.normalizeProperty()} move" }
            evolutionDetail.knownMove?.let { condition += "\nKnown ${it.normalizeProperty()}" }
            evolutionDetail.happiness?.let { condition += "\nHappiness $it" }
            evolutionDetail.beauty?.let { condition += "\nBeauty $it" }
            evolutionDetail.affection?.let { condition += "\nAffection $it" }
            if (evolutionDetail.needsOverWorldRain) { condition += "\nNeeds over world rain" }
            evolutionDetail.gender?.let {
                condition += "\n${if (it == 0) "Male" else "Female"} gender"
            }
            evolutionDetail.relativePhysicalStats?.let {
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

        val evolution = Evolution(
            evolutionChain.id,
            evolutionChain.name,
            evolve.id,
            evolve.name,
            condition
        )
        evolutions.add(evolution)
        mapEvolutionChainToList(evolve, evolutions)
    }
}

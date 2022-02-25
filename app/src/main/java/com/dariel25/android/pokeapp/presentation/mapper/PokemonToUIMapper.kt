package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.core.BaseMapper
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
            it.normalizeProperty().uppercase()
        }

        val types = obj.types.map {
            it.capitalizeFirst()
        }

        val evolutions: ArrayList<Evolution> = arrayListOf()
        mapEvolutionChainToList(obj.evolutionChain, evolutions)

        val genderRateText = if (obj.genderRate != -1) {
            "${(8 - obj.genderRate) * 12.5F}% male, ${obj.genderRate * 12.5F}% female"
        } else {
            "Genderless"
        }

        val capturePercent = String.format("%.2f", obj.captureRate / 255F * 33.33F)
        val captureRateString = "${obj.captureRate} ($capturePercent%)"

        val eggGroupsText = obj.eggGroups.joinToString(", ") { it.normalizeProperty() }

        val hatchCounterText = "${obj.hatchCounter} (${(obj.hatchCounter-1) * 257}-${obj.hatchCounter * 257} steps)"

        return PokemonUI(
            PokemonUtils.getIdTitle(obj.id),
            obj.name.normalizeProperty(),
            obj.specie.normalizeProperty(),
            obj.desc.replace('\n', ' '),
            PokemonUtils.getFormattedHeight(obj.height),
            PokemonUtils.getFormattedWeight(obj.weight),
            PokemonUtils.getImageUrl(obj.id),
            color,
            types,
            stats,
            abilities,
            evolutions,
            obj.isLegendary,
            obj.isBaby,
            obj.isMythical,
            obj.baseExperience.toString(),
            eggGroupsText,
            obj.growthRate.normalizeProperty(),
            genderRateText,
            captureRateString,
            obj.baseHappiness.toString(),
            hatchCounterText,
            obj.generation,
            obj.habitat,
            obj.isFavorite
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
}
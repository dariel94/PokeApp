package com.dariel94.android.pokeapp.presentation.mapper

import android.content.Context
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.presentation.utils.LanguageUtils
import com.dariel94.android.pokeapp.domain.model.EvolutionChain
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.domain.model.Stat
import com.dariel94.android.pokeapp.presentation.model.Evolution
import com.dariel94.android.pokeapp.presentation.model.PokemonUI
import com.dariel94.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel94.android.pokeapp.presentation.utils.capitalizeFirst
import com.dariel94.android.pokeapp.presentation.utils.normalizeProperty

/**
 * Created by dariel94 on 31/10/2021.
 */
fun Pokemon.toUI(context: Context): PokemonUI {
    val lan = LanguageUtils.getLanguage(context)

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
    mapEvolutionChainToList(this.evolutionChain, evolutions, context)

    val maleRatio = "${(8 - this.genderRate) * 12.5F}%"
    val femaleRatio = "${this.genderRate * 12.5F}%"
    val isGenderless = this.genderRate == -1

    val capturePercent = String.format("%.2f", this.captureRate / 255F * 33.33F)
    val captureRateString = "${this.captureRate} ($capturePercent%)"

    val eggGroupsText = this.eggGroups.joinToString(", ") { it.normalizeProperty() }

    val hatchCounterText = "${this.hatchCounter} (${(this.hatchCounter-1) * 257}-${this.hatchCounter * 257} ${context.getString(R.string.pokeapp_steps)})"

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
        PokemonUtils.getGrowthRateTranslation(this.growthRate, lan),
        maleRatio,
        femaleRatio,
        isGenderless,
        captureRateString,
        this.baseHappiness.toString(),
        hatchCounterText,
        this.generation,
        this.habitat,
        this.isFavorite,
        this.varieties.filter { !it.isDefault }
    )
}

private fun mapEvolutionChainToList(
    evolutionChain: EvolutionChain,
    evolutions: ArrayList<Evolution>,
    context: Context,
) {
    val lan = LanguageUtils.getLanguage(context)
    for (evolve in evolutionChain.evolvesTo) {

        var condition = ""
        evolve.evolutionDetail?.let { evolutionDetail ->
            evolutionDetail.trigger?.let { condition += PokemonUtils.getTriggerDescription(it, lan) + " " }
            evolutionDetail.level?.let { condition += it }
            evolutionDetail.item?.let { condition += "\n${it.normalizeProperty()}" }
            evolutionDetail.timeOfDay?.let {
                if (it.isNotEmpty()) condition += context.getString(R.string.pokeapp_at) + " " + PokemonUtils.getTimeOfDayTranslation(it, lan)
            }
            evolutionDetail.heldItem?.let { condition += "\n ${context.getString(R.string.pokeapp_held)} ${it.normalizeProperty()}" }
            evolutionDetail.location?.let { condition += "\n${context.getString(R.string.pokeapp_in)} ${it.normalizeProperty()}" }
            evolutionDetail.knownMoveType?.let {
                condition += if (lan == "es") {
                    "\n${context.getString(R.string.pokeapp_known)} ${context.getString(R.string.pokeapp_move)} ${PokemonUtils.getTypeTranslation(it, lan)} "
                } else {
                    "\n${context.getString(R.string.pokeapp_known)} ${it.normalizeProperty()} ${context.getString(R.string.pokeapp_move)}"
                }
            }
            evolutionDetail.knownMove?.let { condition += "\n${context.getString(R.string.pokeapp_known)} ${it.normalizeProperty()}" }
            evolutionDetail.happiness?.let { condition += "\n${context.getString(R.string.pokeapp_happiness)} $it" }
            evolutionDetail.beauty?.let { condition += "\n${context.getString(R.string.pokeapp_beauty)} $it" }
            evolutionDetail.affection?.let { condition += "\n${context.getString(R.string.pokeapp_affection)} $it" }
            if (evolutionDetail.needsOverWorldRain) { condition += "\n${context.getString(R.string.pokeapp_needs_over_world_rain)}" }
            evolutionDetail.gender?.let {
                condition += "\n${if (it == 0) context.getString(R.string.pokeapp_male) else context.getString(R.string.pokeapp_female)} ${context.getString(R.string.pokeapp_gender)}"
            }
            evolutionDetail.relativePhysicalStats?.let {
                val cond = when (it) {
                    0 -> "="
                    1 -> ">"
                    else -> "<"
                }
                condition += "\n${context.getString(R.string.pokeapp_if)} ${context.getString(R.string.pokeapp_attack)} $cond ${context.getString(R.string.pokeapp_defense)}"
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
        mapEvolutionChainToList(evolve, evolutions, context)
    }
}

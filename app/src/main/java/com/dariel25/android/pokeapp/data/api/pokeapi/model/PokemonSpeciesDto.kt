package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class PokemonSpeciesDto(
    val id: String = "",
    val name: String = "",
    val color: Resource?,
    val generation: Resource?,
    val habitat: Resource?,
    val genera: List<GeneraTextEntry>,
    val varieties: List<VarietyDto>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("is_legendary") val isLegendary: Boolean = false,
    @SerializedName("is_baby") val isBaby: Boolean = false,
    @SerializedName("is_mythical") val isMythical: Boolean = false,
    @SerializedName("evolution_chain") val evolutionChain: Resource,
    @SerializedName("egg_groups") val eggGroups: List<Resource>,
    @SerializedName("growth_rate") val growthRate: Resource?,
    @SerializedName("gender_rate") val genderRate: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("hatch_counter") val hatchCounter: Int
) : Parcelable

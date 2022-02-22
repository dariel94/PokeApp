package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class EvolutionDetailDto(
    @SerializedName("min_level") val level: Int?,
    @SerializedName("min_happiness") val happiness: Int?,
    @SerializedName("min_affection") val affection: Int?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("relative_physical_stats") val relativePhysicalStats: Int?,
    @SerializedName("needs_overworld_rain") val needsOverWorldRain: Boolean,
    @SerializedName("item") val item: Resource?,
    @SerializedName("held_item") val heldItem: Resource?,
    @SerializedName("known_move_type") val knownMoveType: Resource?,
    @SerializedName("known_move") val knownMove: Resource?,
    @SerializedName("time_of_day") val timeOfDay: String?,
    @SerializedName("min_beauty") val beauty: String?,
    @SerializedName("location") val location: Resource?,
    @SerializedName("trigger") val trigger: Resource?,
) : Parcelable
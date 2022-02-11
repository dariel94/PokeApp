package com.dariel25.android.pokeapp.data.network.pokeapi.model

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
    @SerializedName("item") val item: Resource?,
    @SerializedName("held_item") val heldItem: Resource?,
    @SerializedName("trigger") val trigger: Resource?,
) : Parcelable
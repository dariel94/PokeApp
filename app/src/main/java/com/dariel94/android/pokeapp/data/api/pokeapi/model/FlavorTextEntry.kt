package com.dariel94.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String = "",
    val language: Resource,
    val version: Resource
) : Parcelable

package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String = "",
    val language: Resource
) : Parcelable

package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatSlot(
    @SerializedName("base_stat") val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: Resource
) : Parcelable

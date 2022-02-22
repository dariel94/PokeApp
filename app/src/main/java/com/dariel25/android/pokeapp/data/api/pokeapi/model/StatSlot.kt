package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class StatSlot(
    @SerializedName("base_stat") val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: Resource
) : Parcelable

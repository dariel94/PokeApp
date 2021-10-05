package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AbilitySlot(
    @SerializedName("is_hidden") val isHidden: Boolean = false,
    val ability: Resource
) : Parcelable

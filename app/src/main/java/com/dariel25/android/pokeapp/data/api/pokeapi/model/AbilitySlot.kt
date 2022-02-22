package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class AbilitySlot(
    @SerializedName("is_hidden") val isHidden: Boolean = false,
    val ability: Resource
) : Parcelable

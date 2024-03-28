/*
 * Created by dariel94 on 4/6/22 12:02
 * Last modified 4/6/22 12:02
 */

package com.dariel94.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VarietyDto(
    @SerializedName("is_default") val isDefault: Boolean,
    val pokemon: Resource
): Parcelable

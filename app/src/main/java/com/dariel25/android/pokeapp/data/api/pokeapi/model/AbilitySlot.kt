package com.dariel25.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class AbilitySlot(
    val ability: Resource
) : Parcelable

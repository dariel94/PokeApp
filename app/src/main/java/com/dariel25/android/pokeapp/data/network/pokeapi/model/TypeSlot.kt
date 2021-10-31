package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class TypeSlot(
    val type: Resource
) : Parcelable

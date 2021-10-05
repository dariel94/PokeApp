package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeSlot(
    val type: Resource
) : Parcelable

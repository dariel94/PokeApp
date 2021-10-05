package com.dariel25.android.pokeapp.data.network.pokeapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resource(
    val name: String,
    val url: String
) : Parcelable

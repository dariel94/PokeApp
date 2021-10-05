package com.dariel25.android.pokeapp.data.network.pokeapi.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatSlot(
    val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: Resource
)

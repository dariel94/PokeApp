package com.dariel25.android.pokeapp.data.network.pokeapi.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class AbilitySlot(
    val isHidden: Boolean = false,
    val ability: Resource
)

package com.dariel25.android.pokeapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stat(
    val name: String = "",
    val value: Int = 0
) : Parcelable
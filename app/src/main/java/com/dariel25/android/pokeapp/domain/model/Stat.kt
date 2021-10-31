package com.dariel25.android.pokeapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class Stat(
    val name: String = "",
    val value: Int = 0
) : Parcelable
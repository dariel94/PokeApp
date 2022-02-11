package com.dariel25.android.pokeapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class EvolutionChain(
    val id: String = "",
    val name: String = "",
    val level: Int,
    val evolvesTo: List<EvolutionChain>
) : Parcelable

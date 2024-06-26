package com.dariel94.android.pokeapp.data.api.pokeapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by dariel94 on 31/10/2021.
 */
@Parcelize
data class PokemonDto(
    val id: String = "",
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val abilities: List<AbilitySlot>,
    @SerializedName("base_experience") val baseExperience: Int = 0,
    val species: Resource,
) : Parcelable

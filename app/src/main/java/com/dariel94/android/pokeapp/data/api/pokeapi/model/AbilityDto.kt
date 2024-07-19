/*
 * Created by dariel94 on 4/6/22 12:02
 * Last modified 4/6/22 12:02
 */

package com.dariel94.android.pokeapp.data.api.pokeapi.model

import com.google.gson.annotations.SerializedName

data class AbilityDto(
    val name: String = "",
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<AbilityFlavorTextDto>?,
    val names: List<AbilityNameDto>?,
)

/*
 * Created by dariel94 on 4/6/22 12:07
 * Last modified 4/6/22 12:07
 */

package com.dariel94.android.pokeapp.data.api.pokeapi.model

import com.google.gson.annotations.SerializedName

data class AbilityFlavorTextDto(
    @SerializedName("flavor_text") val flavorText: String = "",
    val language: Resource
)

/*
 * Created by dariel94 on 4/6/22 12:02
 * Last modified 4/6/22 12:02
 */

package com.dariel25.android.pokeapp.data.api.pokeapi.model

import com.google.gson.annotations.SerializedName

data class AbilityDto(
    val name: String = "",
    @SerializedName("effect_entries") val effectEntries: List<AbilityEffectDto>?
)

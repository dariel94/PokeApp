/*
 * Created by David on 18/2/22 21:46
 */

package com.dariel94.android.pokeapp.data.mapper

import com.dariel94.android.pokeapp.data.api.pokeapi.model.AbilityDto
import com.dariel94.android.pokeapp.domain.model.Ability

fun AbilityDto.toDomain(): Ability {
    var effect = ""
    this.effectEntries?.forEach {
        if (it.language.name == "en") {
            effect = it.effect
            return@forEach
        }
    }
    return Ability(this.name, effect)
}

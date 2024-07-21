/*
 * Created by David on 18/2/22 21:46
 */

package com.dariel94.android.pokeapp.data.mapper

import com.dariel94.android.pokeapp.data.api.pokeapi.model.AbilityDto
import com.dariel94.android.pokeapp.data.utils.StringUtils.ENGLISH
import com.dariel94.android.pokeapp.domain.model.Ability

fun AbilityDto.toDomain(lan: String): Ability {
    return Ability(this.getAbilityName(lan), this.getAbilityDesc(lan))
}

private fun AbilityDto.getAbilityName(lan: String): String {
    this.names?.find {
        it.language.name == lan
    }?.let {
        return it.name
    }
    this.names?.find {
        it.language.name == ENGLISH
    }?.let {
        return it.name
    }
    return this.names?.getOrNull(0)?.name ?: ""
}

private fun AbilityDto.getAbilityDesc(lan: String): String {
    this.flavorTextEntries?.find {
        it.language.name == lan
    }?.let {
        return it.flavorText.replace("\n", " ")
    }
    this.flavorTextEntries?.find {
        it.language.name == ENGLISH
    }?.let {
        return it.flavorText.replace("\n", " ")
    }
    return this.flavorTextEntries?.getOrNull(0)?.flavorText?.replace("\n", " ") ?: ""
}

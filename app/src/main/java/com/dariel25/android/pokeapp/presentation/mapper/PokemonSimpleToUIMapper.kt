package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.core.BaseMapper
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI

/**
 * Created by dariel94 on 31/10/2021.
 */
object PokemonSimpleToUIMapper : BaseMapper<PokemonSimple, PokemonSimpleUI> {
    override fun map(type: PokemonSimple): PokemonSimpleUI {
        return PokemonSimpleUI(
            type.id,
            type.name,
            type.type1,
            type.type2,
            PokemonUtils.getImageUrl(type.id),
            PokemonUtils.getPokemonTypeColor(type.type1)
        )
    }
}
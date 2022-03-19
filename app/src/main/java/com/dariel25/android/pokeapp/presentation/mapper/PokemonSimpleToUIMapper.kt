package com.dariel25.android.pokeapp.presentation.mapper

import com.dariel25.android.pokeapp.core.BaseMapper
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI

/**
 * Created by dariel94 on 31/10/2021.
 */
object PokemonSimpleToUIMapper : BaseMapper<PokemonSimple, PokemonSimpleUI> {
    override fun map(obj: PokemonSimple): PokemonSimpleUI {
        return PokemonSimpleUI(
            obj.id,
            obj.name,
            obj.type1,
            obj.type2,
            PokemonUtils.getImageUrl(obj.id),
            obj.generation.toString(),
            PokemonUtils.getPokemonTypeColor(obj.type1)
        )
    }
}
/*
 * Created by dariel94 on 21/2/22 05:51
 * Last modified 21/2/22 05:51
 */

package com.dariel94.android.pokeapp.data.source

import com.dariel94.android.pokeapp.data.api.pokelist.PokeList
import com.dariel94.android.pokeapp.data.api.pokelist.model.PokemonSimpleDto
import javax.inject.Inject

class PokemonListRemoteDataSource @Inject constructor(
    private val pokeList: PokeList
) {
    suspend fun getPokemonList(): List<PokemonSimpleDto> {
        return pokeList.getPokemonList()
    }
}
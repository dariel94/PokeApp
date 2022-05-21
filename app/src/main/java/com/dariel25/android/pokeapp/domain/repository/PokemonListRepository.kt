package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.model.PokemonSimple

/**
 * Created by dariel94 on 31/10/2021.
 */
interface PokemonListRepository {

    suspend fun getPokemonList(): NetworkState<List<PokemonSimple>>
}

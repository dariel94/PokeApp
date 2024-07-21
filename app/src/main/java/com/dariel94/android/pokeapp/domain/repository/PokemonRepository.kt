package com.dariel94.android.pokeapp.domain.repository

import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.Pokemon

/**
 * Created by dariel94 on 31/10/2021.
 */
interface PokemonRepository {

    suspend fun getPokemon(id: String, lan: String): NetworkState<Pokemon>

    suspend fun getFavorites(): List<String>?

    suspend fun setFavorite(id: String, value: Boolean)
}

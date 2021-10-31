package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.model.Pokemon

interface PokeApiRepository {

    suspend fun getPokemon(id: String): Pokemon
}

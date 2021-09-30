package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.model.SimplePokemon

interface SimplePokemonRepository {

    suspend fun getSimplePokemon(id: String): SimplePokemon
}
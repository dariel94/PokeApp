package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.model.SimplePokemon

interface PokemonListRepository {

    suspend fun getPokemonList(): List<SimplePokemon>
}

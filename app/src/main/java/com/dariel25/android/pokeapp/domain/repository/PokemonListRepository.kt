package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.model.PokemonSimple

interface PokemonListRepository {

    suspend fun getPokemonList(): List<PokemonSimple>
}
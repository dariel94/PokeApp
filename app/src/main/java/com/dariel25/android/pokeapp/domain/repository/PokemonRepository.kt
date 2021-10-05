package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemon(): Pokemon
}
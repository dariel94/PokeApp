package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.mapper.PokemonSimpleMapper
import com.dariel25.android.pokeapp.data.network.github.PokeListApi
import com.dariel25.android.pokeapp.data.network.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import com.dariel25.android.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val mapper: PokemonSimpleMapper
) : PokemonRepository {

    override suspend fun getPokemon(): Pokemon {
        TODO("Not yet implemented")
    }
}

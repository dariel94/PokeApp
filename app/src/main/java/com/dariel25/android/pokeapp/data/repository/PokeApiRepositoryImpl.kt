package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.network.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.network.pokeapi.model.mapToDomain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.repository.PokeApiRepository
import javax.inject.Inject

class PokeApiRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokeApiRepository {

    override suspend fun getPokemon(id: String): Pokemon {
        return api.getPokemon(id).mapToDomain()
    }
}

package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.mapper.SimplePokemonMapper
import com.dariel25.android.pokeapp.data.network.PokeApi
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.SimplePokemonRepository
import javax.inject.Inject

class SimplePokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val dao: PokemonSimpleDao?,
    private val pokemonMapper: SimplePokemonMapper
) : SimplePokemonRepository {

    override suspend fun getSimplePokemon(id: String): SimplePokemon {
        return pokemonMapper.mapDtoToUI(api.getPokemon(id))
    }
}

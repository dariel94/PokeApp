package com.dariel94.android.pokeapp.data.repository

import com.dariel94.android.pokeapp.data.database.model.mapToDomain
import com.dariel94.android.pokeapp.data.source.PokemonCacheDataSource
import com.dariel94.android.pokeapp.data.source.PokemonRemoteDataSource
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val cacheDataSource: PokemonCacheDataSource
) : PokemonRepository {

    override suspend fun getPokemon(id: String): Pokemon {
        val cachedPokemon = cacheDataSource.getPokemon(id)

        return if (cachedPokemon == null) {
            val remotePokemon = remoteDataSource.getPokemon(id)
            cacheDataSource.insertPokemon(remotePokemon.mapToEntity())
            remotePokemon
        } else {
            cachedPokemon.mapToDomain()
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        cacheDataSource.updatePokemon(pokemon.mapToEntity())
    }
}

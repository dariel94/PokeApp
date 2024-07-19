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

    override suspend fun getPokemon(id: String, lan: String): Pokemon {
        if (USE_CACHE) {
            cacheDataSource.getPokemon(id)?.let {
                return it.mapToDomain()
            }
        }
        val remotePokemon = remoteDataSource.getPokemon(id, lan)
        if (USE_CACHE) {
            cacheDataSource.insertPokemon(remotePokemon.mapToEntity())
        }
        return remotePokemon
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        cacheDataSource.updatePokemon(pokemon.mapToEntity())
    }

    companion object {
        private const val USE_CACHE = false
    }
}

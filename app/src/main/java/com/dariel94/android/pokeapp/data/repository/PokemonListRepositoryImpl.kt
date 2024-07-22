package com.dariel94.android.pokeapp.data.repository

import android.util.Log
import com.dariel94.android.pokeapp.data.api.pokelist.model.mapToDomain
import com.dariel94.android.pokeapp.data.api.pokelist.model.mapToEntity
import com.dariel94.android.pokeapp.data.database.model.mapToPokemonSimple
import com.dariel94.android.pokeapp.data.source.PokemonListCacheDataSource
import com.dariel94.android.pokeapp.data.source.PokemonListRemoteDataSource
import com.dariel94.android.pokeapp.data.utils.StringUtils
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.PokemonSimple
import com.dariel94.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListRepositoryImpl @Inject constructor(
    private val cacheDataSource: PokemonListCacheDataSource,
    private val remoteDataSource: PokemonListRemoteDataSource
) : PokemonListRepository {

    override suspend fun getPokemonList(): NetworkState<List<PokemonSimple>> =
        try {
            val pokemonSimpleDtoList = remoteDataSource.getPokemonList()

            val pokemonEntityList = pokemonSimpleDtoList.map { it.mapToEntity() }
            cacheDataSource.insert(pokemonEntityList)

            val pokemonList = pokemonSimpleDtoList.map { it.mapToDomain() }
            NetworkState.Success(pokemonList)

        } catch (e: Throwable) {
            val cachedPokemonList = cacheDataSource.getPokemonList()

            if (cachedPokemonList.isEmpty()) {
                Log.d(StringUtils.ERROR, e.message ?: "")
                NetworkState.Error(e)
            } else {
                val pokemonList = cachedPokemonList.map { it.mapToPokemonSimple() }
                NetworkState.Success(pokemonList)
            }
        }
}

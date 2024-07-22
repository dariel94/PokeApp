package com.dariel94.android.pokeapp.data.repository

import android.util.Log
import com.dariel94.android.pokeapp.data.database.model.PokemonEntity
import com.dariel94.android.pokeapp.data.database.model.mapToDomain
import com.dariel94.android.pokeapp.data.source.PokemonCacheDataSource
import com.dariel94.android.pokeapp.data.source.PokemonRemoteDataSource
import com.dariel94.android.pokeapp.data.utils.StringUtils
import com.dariel94.android.pokeapp.domain.NetworkState
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

    override suspend fun getPokemon(id: String, lan: String): NetworkState<Pokemon> {
        var localPokemon: PokemonEntity? = null

        return try {
            localPokemon = cacheDataSource.getPokemon(id)
            val remotePokemon = remoteDataSource.getPokemon(id, lan)

            if (localPokemon == null) {
                cacheDataSource.insertPokemon(remotePokemon.mapToEntity())
            } else {
                remotePokemon.isFavorite = localPokemon.isFavorite
            }

            NetworkState.Success(remotePokemon)
        } catch (e: Throwable) {
            if (localPokemon != null) {
                NetworkState.Success(localPokemon.mapToDomain())
            } else {
                Log.d(StringUtils.ERROR, e.message ?: "")
                NetworkState.Error(e)
            }
        }
    }

    override suspend fun getFavorites(): NetworkState<List<String>?> {
        return try {
            NetworkState.Success(cacheDataSource.getFavorites())
        } catch (e: Throwable) {
            Log.d(StringUtils.ERROR, e.message ?: "")
            NetworkState.Error(e)
        }
    }

    override suspend fun setFavorite(id: String, value: Boolean) {
        try {
            cacheDataSource.setFavourite(id, value)
        } catch (e: Throwable) {
            Log.d(StringUtils.ERROR, e.message ?: "")
        }

    }
}

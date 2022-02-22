package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.api.pokelist.model.mapToDomain
import com.dariel25.android.pokeapp.data.api.pokelist.model.mapToEntity
import com.dariel25.android.pokeapp.data.database.model.mapToPokemonSimple
import com.dariel25.android.pokeapp.data.source.PokemonListCacheDataSource
import com.dariel25.android.pokeapp.data.source.PokemonListRemoteDataSource
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListRepositoryImpl @Inject constructor(
    private val cacheDataSource: PokemonListCacheDataSource,
    private val remoteDataSource: PokemonListRemoteDataSource
) : PokemonListRepository {

    override suspend fun getPokemonList(): List<PokemonSimple> {
        val cachedList = cacheDataSource.getPokemonList()

        return if (cachedList.isNullOrEmpty()) {
            val remoteList = remoteDataSource.getPokemonList()
            cacheDataSource.insert(remoteList.map { it.mapToEntity() })
            remoteList.map { it.mapToDomain() }
        } else {
            cachedList.map { it.mapToPokemonSimple() }
        }
    }
}

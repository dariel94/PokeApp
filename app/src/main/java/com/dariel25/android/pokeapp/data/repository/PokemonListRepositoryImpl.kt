package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.network.pokelist.PokeList
import com.dariel25.android.pokeapp.data.network.pokelist.model.mapToDomain
import com.dariel25.android.pokeapp.data.network.pokelist.model.mapToEntity
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.data.room.model.mapToPokemonSimple
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListRepositoryImpl @Inject constructor(
    private val api: PokeList,
    private val dao: PokemonSimpleDao
) : PokemonListRepository {

    override suspend fun getPokemonList(): List<PokemonSimple> {
        val cachedList = dao.getAll()

        return if (cachedList.isNullOrEmpty()) {
            val remoteList = api.getPokemonList()
            dao.insert(remoteList.map { it.mapToEntity() })

            api.getPokemonList().map { it.mapToDomain() }
        } else {
            cachedList.map { it.mapToPokemonSimple() }
        }
    }
}
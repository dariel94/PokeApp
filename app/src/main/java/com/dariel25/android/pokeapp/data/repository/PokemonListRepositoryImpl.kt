package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.mapper.PokemonSimpleMapper
import com.dariel25.android.pokeapp.data.network.PokeListApi
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val api: PokeListApi,
    private val dao: PokemonSimpleDao?,
    private val mapper: PokemonSimpleMapper
) : PokemonListRepository {

    override suspend fun getPokemonList(): List<PokemonSimple> {
        val cachedList = dao?.getAll()

        return if (cachedList.isNullOrEmpty()) {
            val remoteList = api.getPokemonList()
            dao?.insert(mapper.mapDtoToEntity(remoteList))
            mapper.mapDtoToUI(api.getPokemonList())
        } else {
            mapper.mapEntityToUI(cachedList)
        }
    }
}

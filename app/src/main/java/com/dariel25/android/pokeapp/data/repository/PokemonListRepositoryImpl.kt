package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.mapper.PokemonSimpleMapper
import com.dariel25.android.pokeapp.data.network.pokelist.PokeList
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val api: PokeList,
    private val dao: PokemonSimpleDao,
    private val mapper: PokemonSimpleMapper
) : PokemonListRepository {

    override suspend fun getPokemonList(): List<SimplePokemon> {
        val cachedList = dao.getAll()

        return if (cachedList.isNullOrEmpty()) {
            val remoteList = api.getPokemonList()
            dao.insert(mapper.mapDtoToEntity(remoteList))
            mapper.mapDtoToUI(api.getPokemonList())
        } else {
            mapper.mapEntityToUI(cachedList)
        }
    }
}
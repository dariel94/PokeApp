package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.mapper.SimplePokemonMapper
import com.dariel25.android.pokeapp.data.network.PokeApi
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val dao: PokemonSimpleDao?,
    private val pokemonMapper: SimplePokemonMapper
) : PokemonListRepository {

    override suspend fun getPokemonList(): List<SimplePokemon> {
        val cachedList = dao?.getAll()

        return if (cachedList.isNullOrEmpty()) {
            val count = api.getPokemonList(0, 0).count
            val remoteList = api.getPokemonList(0, count).results
            dao?.insert(pokemonMapper.mapDtoToEntity(remoteList))
            pokemonMapper.mapDtoToUI(remoteList)
        } else {
            pokemonMapper.mapEntityToUI(cachedList)
        }
    }
}

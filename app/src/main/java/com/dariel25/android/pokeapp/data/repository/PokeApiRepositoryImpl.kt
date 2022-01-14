package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.network.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.network.pokeapi.model.mapToDomain
import com.dariel25.android.pokeapp.data.network.pokeapi.model.mapToEntity
import com.dariel25.android.pokeapp.data.room.PokemonDao
import com.dariel25.android.pokeapp.data.room.model.mapToDomain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.repository.PokeApiRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokeApiRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val dao: PokemonDao
) : PokeApiRepository {

    override suspend fun getPokemon(id: String): Pokemon {
        val cachedPokemon = dao.getById(id)

        return if (cachedPokemon == null) {
            val remotePokemon = api.getPokemon(id)
            val pokemonSpecies = api.getPokemonSpecies(id)
            val pokemonChainId = pokemonSpecies.evolutionChain.url.split('/').last()
            val evolutionChain = api.getEvolutionChain(pokemonChainId)
            //dao.insert(remotePokemon.mapToEntity())
            remotePokemon.mapToDomain()
        } else {
            cachedPokemon.mapToDomain()
        }
    }
}

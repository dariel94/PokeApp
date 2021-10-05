package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.network.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.network.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.domain.repository.PokeApiRepository
import javax.inject.Inject

class PokeApiRepositoryImpl @Inject constructor(
    private val api: PokeApi
) : PokeApiRepository {

    override suspend fun getPokemon(id: String): PokemonDto {
        return api.getPokemon(id)
    }

    override suspend fun getPokemonSpecies(id: String): PokemonSpeciesDto {
        return api.getPokemonSpecies(id)
    }

    override suspend fun getEvolutionChain(id: String): EvolutionChainDto {
        return api.getEvolutionChain(id)
    }
}

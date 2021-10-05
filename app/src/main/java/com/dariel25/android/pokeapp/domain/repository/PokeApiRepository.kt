package com.dariel25.android.pokeapp.domain.repository

import com.dariel25.android.pokeapp.data.network.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto

interface PokeApiRepository {

    suspend fun getPokemon(id: String): PokemonDto

    suspend fun getPokemonSpecies(id: String): PokemonSpeciesDto

    suspend fun getEvolutionChain(id: String): EvolutionChainDto
}

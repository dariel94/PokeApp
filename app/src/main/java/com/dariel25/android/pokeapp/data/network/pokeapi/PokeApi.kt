package com.dariel25.android.pokeapp.data.network.pokeapi

import com.dariel25.android.pokeapp.data.network.pokeapi.model.EvolutionChainDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): PokemonDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: String
    ): PokemonSpeciesDto

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: String
    ): EvolutionChainDto
}

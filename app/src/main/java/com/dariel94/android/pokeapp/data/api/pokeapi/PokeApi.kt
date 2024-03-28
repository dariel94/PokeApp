package com.dariel94.android.pokeapp.data.api.pokeapi

import com.dariel94.android.pokeapp.data.api.pokeapi.model.*
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by dariel94 on 31/10/2021.
 */
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

    @GET("generation")
    suspend fun getGenerations(): ResourcesResponse

    @GET("type")
    suspend fun getTypes(): ResourcesResponse

    @GET("ability/{name}")
    suspend fun getAbility(
        @Path("name") name: String
    ): AbilityDto
}

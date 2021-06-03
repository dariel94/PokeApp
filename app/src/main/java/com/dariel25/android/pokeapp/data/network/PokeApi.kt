package com.dariel25.android.pokeapp.data.network

import com.dariel25.android.pokeapp.data.network.model.PokemonSimpleDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): List<PokemonSimpleDto>

}
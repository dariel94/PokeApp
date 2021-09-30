package com.dariel25.android.pokeapp.data.network

import com.dariel25.android.pokeapp.data.network.model.ListResponse
import com.dariel25.android.pokeapp.data.network.model.SimplePokemonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): SimplePokemonDto

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ListResponse
}

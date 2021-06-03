package com.dariel25.android.pokeapp.data.network

import com.dariel25.android.pokeapp.data.network.model.PokemonSimpleDto
import retrofit2.http.GET

interface PokeListApi {

    @GET("pokemon.json")
    suspend fun getPokemonList(): List<PokemonSimpleDto>

}
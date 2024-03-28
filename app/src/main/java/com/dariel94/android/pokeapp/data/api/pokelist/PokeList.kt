package com.dariel94.android.pokeapp.data.api.pokelist

import com.dariel94.android.pokeapp.data.api.pokelist.model.PokemonSimpleDto
import retrofit2.http.GET

/**
 * Created by dariel94 on 31/10/2021.
 */
interface PokeList {

    @GET("pokemon.json")
    suspend fun getPokemonList(): List<PokemonSimpleDto>

}
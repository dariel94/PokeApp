/*
 * Created by dariel94 on 21/2/22 05:51
 * Last modified 21/2/22 05:51
 */

package com.dariel94.android.pokeapp.data.source

import com.dariel94.android.pokeapp.data.database.PokemonDao
import com.dariel94.android.pokeapp.data.database.model.PokemonEntity
import javax.inject.Inject

class PokemonCacheDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    suspend fun getPokemon(id: String): PokemonEntity? {
        return pokemonDao.getById(id)
    }

    suspend fun insertPokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.insert(pokemonEntity)
    }

    suspend fun updatePokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.update(pokemonEntity)
    }
}

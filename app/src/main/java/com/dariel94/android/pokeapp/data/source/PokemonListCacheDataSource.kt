/*
 * Created by dariel94 on 21/2/22 05:51
 * Last modified 21/2/22 05:51
 */

package com.dariel94.android.pokeapp.data.source

import com.dariel94.android.pokeapp.data.database.PokemonSimpleDao
import com.dariel94.android.pokeapp.data.database.model.PokemonSimpleEntity
import javax.inject.Inject

class PokemonListCacheDataSource @Inject constructor(
    private val dao: PokemonSimpleDao
) {
    suspend fun getPokemonList(): List<PokemonSimpleEntity> {
        return dao.getAll()
    }

    suspend fun insert(list: List<PokemonSimpleEntity>) {
        dao.insert(list)
    }
}

package com.dariel94.android.pokeapp.data.database

import androidx.room.*
import com.dariel94.android.pokeapp.data.database.model.PokemonSimpleEntity

/**
 * Created by dariel94 on 31/10/2021.
 */
@Dao
interface PokemonSimpleDao {

    @Query("SELECT * FROM PokemonSimpleEntity")
    suspend fun getAll(): List<PokemonSimpleEntity>

    @Query("SELECT * FROM PokemonSimpleEntity WHERE id = :id")
    suspend fun getById(id: String): PokemonSimpleEntity

    @Update
    suspend fun update(pokemonSimpleEntity: PokemonSimpleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<PokemonSimpleEntity>)

    @Delete
    suspend fun delete(pokemonSimpleEntity: PokemonSimpleEntity)
}
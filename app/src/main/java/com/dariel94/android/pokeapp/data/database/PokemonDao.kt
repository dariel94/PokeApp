package com.dariel94.android.pokeapp.data.database

import androidx.room.*
import com.dariel94.android.pokeapp.data.database.model.PokemonEntity

/**
 * Created by dariel94 on 31/10/2021.
 */
@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun getById(id: String): PokemonEntity?

    @Update
    suspend fun update(pokemonEntity: PokemonEntity)

    @Insert
    suspend fun insert(list: PokemonEntity)

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)
}

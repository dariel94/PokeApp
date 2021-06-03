package com.dariel25.android.pokeapp.data.room

import androidx.room.*
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity

@Dao
interface PokemonSimpleDao {

    @Query("SELECT * FROM PokemonSimpleEntity")
    suspend fun getAll(): List<PokemonSimpleEntity>

    @Query("SELECT * FROM PokemonSimpleEntity WHERE id = :id")
    suspend fun getById(id: String): PokemonSimpleEntity

    @Update
    suspend fun update(pokemonSimpleEntity: PokemonSimpleEntity)

    @Insert
    suspend fun insert(list: List<PokemonSimpleEntity>)

    @Delete
    suspend fun delete(pokemonSimpleEntity: PokemonSimpleEntity)
}
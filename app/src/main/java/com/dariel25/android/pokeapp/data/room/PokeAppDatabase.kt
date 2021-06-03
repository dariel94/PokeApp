package com.dariel25.android.pokeapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity

@Database(
    entities = [PokemonSimpleEntity::class],
    version = 1
)
abstract class PokeAppDatabase : RoomDatabase() {

    abstract fun pokemonSimpleDao(): PokemonSimpleDao
}
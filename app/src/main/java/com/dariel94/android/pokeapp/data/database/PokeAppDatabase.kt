package com.dariel94.android.pokeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dariel94.android.pokeapp.data.database.model.Converters
import com.dariel94.android.pokeapp.data.database.model.PokemonEntity
import com.dariel94.android.pokeapp.data.database.model.PokemonSimpleEntity

/**
 * Created by dariel94 on 31/10/2021.
 */
@Database(
    entities = [PokemonSimpleEntity::class, PokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PokeAppDatabase : RoomDatabase() {

    abstract fun pokemonSimpleDao(): PokemonSimpleDao

    abstract fun pokemonDao(): PokemonDao
}
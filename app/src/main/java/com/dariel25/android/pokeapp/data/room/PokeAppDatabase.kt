package com.dariel25.android.pokeapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dariel25.android.pokeapp.data.room.model.Converters
import com.dariel25.android.pokeapp.data.room.model.PokemonEntity
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity

/**
 * Created by dariel94 on 31/10/2021.
 */
@Database(
    entities = [PokemonSimpleEntity::class, PokemonEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PokeAppDatabase : RoomDatabase() {

    abstract fun pokemonSimpleDao(): PokemonSimpleDao

    abstract fun pokemonDao(): PokemonDao
}
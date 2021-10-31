package com.dariel25.android.pokeapp.di

import android.content.Context
import androidx.room.Room
import com.dariel25.android.pokeapp.data.room.PokeAppDatabase
import com.dariel25.android.pokeapp.data.room.PokemonDao
import com.dariel25.android.pokeapp.data.room.PokemonSimpleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by dariel94 on 31/10/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    internal fun providesPokeAppDatabase(@ApplicationContext context: Context): PokeAppDatabase =
        Room.databaseBuilder(context, PokeAppDatabase::class.java, "pokemon")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    internal fun providesPokemonSimpleDao(pokeAppDatabase: PokeAppDatabase): PokemonSimpleDao =
        pokeAppDatabase.pokemonSimpleDao()

    @Provides
    internal fun providesPokemonDao(pokeAppDatabase: PokeAppDatabase): PokemonDao =
        pokeAppDatabase.pokemonDao()

}
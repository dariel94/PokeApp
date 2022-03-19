package com.dariel25.android.pokeapp.di

import com.dariel25.android.pokeapp.data.repository.GenerationsRepositoryImpl
import com.dariel25.android.pokeapp.data.repository.PokemonRepositoryImpl
import com.dariel25.android.pokeapp.data.repository.PokemonListRepositoryImpl
import com.dariel25.android.pokeapp.data.repository.TypesRepositoryImpl
import com.dariel25.android.pokeapp.domain.repository.GenerationsRepository
import com.dariel25.android.pokeapp.domain.repository.PokemonRepository
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import com.dariel25.android.pokeapp.domain.repository.TypesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by dariel94 on 31/10/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    internal abstract fun providesPokemonListRepository(
        pokemonListRepositoryImpl: PokemonListRepositoryImpl
    ): PokemonListRepository

    @Binds
    internal abstract fun providesPokeApiRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository

    @Binds
    internal abstract fun providesTypesRepository(
        typesRepositoryImpl: TypesRepositoryImpl
    ): TypesRepository

    @Binds
    internal abstract fun providesGenerationsRepository(
        generationsRepositoryImpl: GenerationsRepositoryImpl
    ): GenerationsRepository
}

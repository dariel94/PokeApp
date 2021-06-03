package com.dariel25.android.pokeapp.di

import com.dariel25.android.pokeapp.data.repository.PokemonListRepositoryImpl
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    internal abstract fun providesPokemonListRepository(
        pokemonListRepositoryImpl: PokemonListRepositoryImpl
    ): PokemonListRepository

}
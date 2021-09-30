package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.data.network.model.NetworkState
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(
    private val repository: PokemonListRepository
) {
    suspend fun getPokemonList() : NetworkState<List<SimplePokemon>> = try {
        val pokemonList = withContext(Dispatchers.IO) {
            repository.getPokemonList()
        }
        NetworkState.Success(pokemonList)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}
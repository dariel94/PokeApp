package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.data.network.model.NetworkState
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.repository.SimplePokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimplePokemonUseCase @Inject constructor(
    private val repository: SimplePokemonRepository
) {
    suspend fun getSimplePokemon(id: String) : NetworkState<SimplePokemon> = try {
        val simplePokemon = withContext(Dispatchers.IO) {
            repository.getSimplePokemon(id)
        }
        NetworkState.Success(simplePokemon)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}
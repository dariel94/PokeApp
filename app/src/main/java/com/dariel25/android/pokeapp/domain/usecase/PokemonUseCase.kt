package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun invoke(id: String) : NetworkState<Pokemon> = try {
        val pokemon = withContext(Dispatchers.IO) {
            repository.getPokemon(id)
        }
        NetworkState.Success(pokemon)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }

    suspend fun updatePokemon(pokemon: Pokemon) {
        repository.updatePokemon(pokemon)
    }
}

package com.dariel94.android.pokeapp.domain.usecase

import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun invoke(id: String, lan: String) : NetworkState<Pokemon> {
        return repository.getPokemon(id, lan)
    }
}

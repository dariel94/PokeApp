package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.repository.PokemonListRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListUseCase @Inject constructor(
    private val repository: PokemonListRepository
) {
    suspend fun invoke() : NetworkState<List<PokemonSimple>> {
        return repository.getPokemonList()
    }
}

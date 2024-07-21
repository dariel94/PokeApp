/*
 * Created by dariel94 on 21/7/24 5:21
 * Last modified 21/7/24 5:21
 */

package com.dariel94.android.pokeapp.domain.usecase

import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.repository.PokemonRepository
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend fun invoke() : NetworkState<List<String>?> = repository.getFavorites()

    suspend fun setFavorite(id: String, value: Boolean) {
        repository.setFavorite(id, value)
    }
}

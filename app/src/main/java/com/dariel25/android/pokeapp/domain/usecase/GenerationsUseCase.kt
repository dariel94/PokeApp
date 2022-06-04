/*
 * Created by dariel94 on 14/3/22 05:28
 * Last modified 14/3/22 05:28
 */

package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.repository.GenerationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenerationsUseCase @Inject constructor(
    private val generationsRepository: GenerationsRepository
) {
    suspend fun invoke() : NetworkState<List<String>> = try {
        val generations = withContext(Dispatchers.IO) {
            generationsRepository.getGenerations()
        }
        NetworkState.Success(generations)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}
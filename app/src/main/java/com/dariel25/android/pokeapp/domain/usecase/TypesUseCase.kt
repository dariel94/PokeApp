/*
 * Created by dariel94 on 14/3/22 05:28
 * Last modified 14/3/22 05:28
 */

package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.repository.TypesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TypesUseCase @Inject constructor(
    private val typesRepository: TypesRepository
) {
    suspend fun invoke() : NetworkState<List<String>> = try {
        val types = withContext(Dispatchers.IO) {
            typesRepository.getTypes()
        }
        NetworkState.Success(types)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}
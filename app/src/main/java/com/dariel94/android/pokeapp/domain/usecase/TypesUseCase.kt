/*
 * Created by dariel94 on 14/3/22 05:28
 * Last modified 14/3/22 05:28
 */

package com.dariel94.android.pokeapp.domain.usecase

import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.repository.TypesRepository
import javax.inject.Inject

class TypesUseCase @Inject constructor(
    private val typesRepository: TypesRepository
) {
    suspend fun invoke() : NetworkState<List<String>> = typesRepository.getTypes()
}
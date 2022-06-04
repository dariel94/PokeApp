/*
 * Created by dariel94 on 14/3/22 05:31
 * Last modified 14/3/22 05:31
 */

package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.source.GenerationsRemoteDataSource
import com.dariel25.android.pokeapp.domain.repository.GenerationsRepository
import javax.inject.Inject

class GenerationsRepositoryImpl @Inject constructor(
    private val generationsRemoteDataSource: GenerationsRemoteDataSource
) : GenerationsRepository {

    override suspend fun getGenerations(): List<String> =
        generationsRemoteDataSource.getGenerations().results.map { it.name }

}

/*
 * Created by dariel94 on 14/3/22 05:31
 * Last modified 14/3/22 05:31
 */

package com.dariel94.android.pokeapp.data.repository

import android.util.Log
import com.dariel94.android.pokeapp.data.source.GenerationsRemoteDataSource
import com.dariel94.android.pokeapp.data.utils.StringUtils
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.repository.GenerationsRepository
import javax.inject.Inject

class GenerationsRepositoryImpl @Inject constructor(
    private val generationsRemoteDataSource: GenerationsRemoteDataSource
) : GenerationsRepository {

    override suspend fun getGenerations(): NetworkState<List<String>> {
        return try {
            NetworkState.Success(generationsRemoteDataSource.getGenerations().results.map { it.name })
        } catch (e: Throwable) {
            Log.d(StringUtils.ERROR, e.message ?: "")
            NetworkState.Error(e)
        }
    }
}


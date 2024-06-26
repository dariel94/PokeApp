/*
 * Created by dariel94 on 14/3/22 05:31
 * Last modified 14/3/22 05:31
 */

package com.dariel94.android.pokeapp.data.repository

import com.dariel94.android.pokeapp.data.source.TypesRemoteDataSource
import com.dariel94.android.pokeapp.domain.repository.TypesRepository
import javax.inject.Inject

class TypesRepositoryImpl @Inject constructor(
    private val typesRemoteDataSource: TypesRemoteDataSource
) : TypesRepository {

    override suspend fun getTypes(): List<String> =
        typesRemoteDataSource.getTypes().results.map { it.name }

}

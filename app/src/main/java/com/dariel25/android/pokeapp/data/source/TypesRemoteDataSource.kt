/*
 * Created by dariel94 on 14/3/22 05:33
 * Last modified 14/3/22 05:33
 */

package com.dariel25.android.pokeapp.data.source

import com.dariel25.android.pokeapp.data.api.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.api.pokeapi.model.ResourcesResponse
import javax.inject.Inject

class TypesRemoteDataSource @Inject constructor(
    private val pokeApi: PokeApi
) {
    suspend fun getTypes(): ResourcesResponse {
        return pokeApi.getTypes()
    }
}
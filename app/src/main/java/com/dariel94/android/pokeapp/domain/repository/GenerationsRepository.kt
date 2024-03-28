/*
 * Created by dariel94 on 14/3/22 05:27
 * Last modified 14/3/22 05:27
 */

package com.dariel94.android.pokeapp.domain.repository

interface GenerationsRepository {
    suspend fun getGenerations(): List<String>
}
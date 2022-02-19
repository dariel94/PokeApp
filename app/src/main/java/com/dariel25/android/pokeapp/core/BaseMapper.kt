package com.dariel25.android.pokeapp.core

/**
 * Created by dariel94 on 31/10/2021.
 */
interface BaseMapper<in A, out B> {

    fun map(obj: A): B
}
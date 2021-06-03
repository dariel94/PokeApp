package com.dariel25.android.pokeapp.data.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A): B
}
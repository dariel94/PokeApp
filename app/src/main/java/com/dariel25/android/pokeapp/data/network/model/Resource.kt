package com.dariel25.android.pokeapp.data.network.model

sealed class Resource<out T> {

    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val error: Throwable): Resource<Nothing>()
    object Loading: Resource<Nothing>()

}

package com.dariel25.android.pokeapp.data.network

sealed class NetworkState<out T> {

    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error(val error: Throwable): NetworkState<Nothing>()

}

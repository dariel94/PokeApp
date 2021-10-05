package com.dariel25.android.pokeapp.data.network.model

sealed class NetworkState<out T> {

    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error(val error: Throwable): NetworkState<Nothing>()
    object Loading: NetworkState<Nothing>()

}

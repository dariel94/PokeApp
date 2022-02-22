package com.dariel25.android.pokeapp.domain

/**
 * Created by dariel94 on 31/10/2021.
 */
sealed class NetworkState<out T> {
    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error(val error: Throwable): NetworkState<Nothing>()
}

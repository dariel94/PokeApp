package com.dariel25.android.pokeapp.presentation.model

/**
 * Created by dariel94 on 31/10/2021.
 */
sealed class UIState<out T> {
    data class Success<out T>(val data: T): UIState<T>()
    data class Error(val message: String): UIState<Nothing>()
    object Loading: UIState<Nothing>()
}

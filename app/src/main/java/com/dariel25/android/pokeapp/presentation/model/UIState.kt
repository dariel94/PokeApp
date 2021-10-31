package com.dariel25.android.pokeapp.presentation.model

sealed class UIState<out T> {

    data class Success<out T>(val data: T): UIState<T>()
    data class Error(val message: String): UIState<Nothing>()
    object Loading: UIState<Nothing>()
}

package com.dariel25.android.pokeapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.data.network.NetworkState
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.usecase.PokemonDetailUseCase
import com.dariel25.android.pokeapp.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<Pokemon?>>()

    fun getViewStateLiveData(): LiveData<UIState<Pokemon?>> {
        return mutableViewState
    }

    fun fetchPokemon(id: String) {
        mutableViewState.value = UIState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonDetailUseCase.getPokemon(id)) {
                is NetworkState.Success -> {
                    mutableViewState.value = UIState.Success(networkStatus.data)
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }
}

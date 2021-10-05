package com.dariel25.android.pokeapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.data.network.model.NetworkState
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.usecase.PokemonDetailUseCase
import com.dariel25.android.pokeapp.presentation.model.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<ViewState<Pokemon?>>()

    fun getViewStateLiveData(): LiveData<ViewState<Pokemon?>> {
        return mutableViewState
    }

    fun fetchPokemon(id: String) {
        mutableViewState.value = ViewState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonDetailUseCase.getPokemon(id)) {
                is NetworkState.Success -> {
                    mutableViewState.value = ViewState.Success(networkStatus.data)
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = ViewState.Error(msg)
                }
            }
        }
    }
}

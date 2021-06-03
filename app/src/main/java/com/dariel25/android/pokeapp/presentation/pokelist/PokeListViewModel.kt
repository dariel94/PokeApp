package com.dariel25.android.pokeapp.presentation.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.data.network.model.Resource
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.usecase.PokemonListUseCase
import com.dariel25.android.pokeapp.presentation.model.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<ViewState<List<PokemonSimple>?>>()

    init {
        fetchPokemons()
    }

    fun getViewStateLiveData(): LiveData<ViewState<List<PokemonSimple>?>> {
        return mutableViewState
    }

    fun fetchPokemons() {
        mutableViewState.value = ViewState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonListUseCase.getPokemonList()) {
                is Resource.Success -> {
                    mutableViewState.value = ViewState.Success(networkStatus.data)
                }
                is Resource.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = ViewState.Error(msg)
                }
            }
        }
    }
}

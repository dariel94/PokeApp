package com.dariel25.android.pokeapp.presentation.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.data.network.NetworkState
import com.dariel25.android.pokeapp.domain.usecase.PokemonListUseCase
import com.dariel25.android.pokeapp.presentation.mapper.PokemonSimpleToUIMapper
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<List<PokemonSimpleUI>?>>()

    fun getViewStateLiveData(): LiveData<UIState<List<PokemonSimpleUI>?>> {
        return mutableViewState
    }

    fun fetchPokemons() {
        mutableViewState.value = UIState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonListUseCase.invoke()) {
                is NetworkState.Success -> {
                    mutableViewState.value = UIState.Success(networkStatus.data.map {
                        PokemonSimpleToUIMapper.map(it)
                    })
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }
}

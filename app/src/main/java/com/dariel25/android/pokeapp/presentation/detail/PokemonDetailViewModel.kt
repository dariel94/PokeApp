package com.dariel25.android.pokeapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.usecase.PokemonUseCase
import com.dariel25.android.pokeapp.presentation.mapper.toUI
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<PokemonUI?>>()

    private var pokemon: Pokemon? = null

    fun getViewStateLiveData(): LiveData<UIState<PokemonUI?>> {
        return mutableViewState
    }

    fun fetchPokemon(id: String) {
        mutableViewState.value = UIState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonUseCase.invoke(id)) {
                is NetworkState.Success -> {
                    pokemon = networkStatus.data
                    mutableViewState.value = UIState.Success(pokemon?.toUI())
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }

    fun setFavorite(favorite: Boolean) {
        pokemon?.let {
            it.isFavorite = favorite
            viewModelScope.launch {
                pokemonUseCase.updatePokemon(it)
            }
        }
    }
}

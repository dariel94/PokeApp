package com.dariel94.android.pokeapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.domain.usecase.FavoritesUseCase
import com.dariel94.android.pokeapp.domain.usecase.PokemonUseCase
import com.dariel94.android.pokeapp.presentation.model.UIState
import com.dariel94.android.pokeapp.presentation.utils.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    private val favouritesUseCase: FavoritesUseCase,
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<Pokemon?>>()
    private var pokemon: Pokemon? = null

    fun getViewStateLiveData(): LiveData<UIState<Pokemon?>> {
        return mutableViewState
    }

    fun fetchPokemon(id: String, lan: String) {
        mutableViewState.value = UIState.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonUseCase.invoke(id, lan)) {
                is NetworkState.Success -> {
                    pokemon = networkStatus.data
                    mutableViewState.value = UIState.Success(pokemon)
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: StringUtils.ERROR
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }

    fun setFavorite(favorite: Boolean) {
        pokemon?.let {
            it.isFavorite = favorite
            viewModelScope.launch {
                favouritesUseCase.setFavorite(it.id, favorite)
            }
        }
    }
}

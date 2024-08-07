package com.dariel94.android.pokeapp.presentation.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.usecase.FavoritesUseCase
import com.dariel94.android.pokeapp.domain.usecase.GenerationsUseCase
import com.dariel94.android.pokeapp.domain.usecase.PokemonListUseCase
import com.dariel94.android.pokeapp.domain.usecase.TypesUseCase
import com.dariel94.android.pokeapp.presentation.mapper.PokemonSimpleToUIMapper
import com.dariel94.android.pokeapp.presentation.model.PokeListData
import com.dariel94.android.pokeapp.presentation.model.UIState
import com.dariel94.android.pokeapp.presentation.utils.StringUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
    private val typesUseCase: TypesUseCase,
    private val generationsUseCase: GenerationsUseCase,
    private val favoritesUseCase: FavoritesUseCase,
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<PokeListData>>()

    fun getViewStateLiveData(): LiveData<UIState<PokeListData>> {
        return mutableViewState
    }

    fun fetchPokemonListData() {
        mutableViewState.value = UIState.Loading

        viewModelScope.launch {
            val typesState = typesUseCase.invoke()
            val generationsState = generationsUseCase.invoke()
            val favoritesState = favoritesUseCase.invoke()

            when (val pokemonListState = pokemonListUseCase.invoke()) {
                is NetworkState.Success -> {
                    val types = if (typesState is NetworkState.Success) {
                        typesState.data
                    } else {
                        null
                    }
                    val generations = if (generationsState is NetworkState.Success) {
                        generationsState.data
                    } else {
                        null
                    }
                    val favorites = if (favoritesState is NetworkState.Success) {
                        favoritesState.data
                    } else {
                        null
                    }
                    mutableViewState.value = UIState.Success(
                        PokeListData(
                            pokemonListState.data.map {
                                PokemonSimpleToUIMapper.map(it)
                            },
                            types,
                            generations,
                            listOf(StringUtils.LEGENDARY, StringUtils.FAVORITE),
                            favorites,
                        )
                    )
                }
                is NetworkState.Error -> {
                    val msg = pokemonListState.error.message ?: StringUtils.ERROR
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }

    fun fetchFavorites() {
        viewModelScope.launch {
            when (val favoritesState = favoritesUseCase.invoke()) {
                is NetworkState.Success -> {
                    mutableViewState.value = UIState.Success(
                        PokeListData(
                            null,
                            null,
                            null,
                            null,
                            favoritesState.data,
                        )
                    )
                }
                is NetworkState.Error -> {
                    val msg = favoritesState.error.message ?: StringUtils.ERROR
                    mutableViewState.value = UIState.Error(msg)
                }
            }
        }
    }
}

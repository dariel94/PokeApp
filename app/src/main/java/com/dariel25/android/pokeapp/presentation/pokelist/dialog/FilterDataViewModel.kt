/*
 * Created by dariel94 on 14/3/22 05:47
 * Last modified 14/3/22 05:47
 */

package com.dariel25.android.pokeapp.presentation.pokelist.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariel25.android.pokeapp.domain.NetworkState
import com.dariel25.android.pokeapp.domain.usecase.GenerationsUseCase
import com.dariel25.android.pokeapp.domain.usecase.TypesUseCase
import com.dariel25.android.pokeapp.presentation.model.OptionFilters
import com.dariel25.android.pokeapp.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterDataViewModel @Inject constructor(
    private val typesUseCase: TypesUseCase,
    private val generationsUseCase: GenerationsUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<UIState<OptionFilters>>()

    fun getViewStateLiveData(): LiveData<UIState<OptionFilters>> {
        return mutableViewState
    }

    fun fetchOptionFilters() {
        viewModelScope.launch {
            val types = typesUseCase.invoke()
            val generations = generationsUseCase.invoke()
            mutableViewState.value =
                when {
                    types is NetworkState.Success && generations is NetworkState.Success -> {
                        UIState.Success(OptionFilters(types.data, generations.data))
                    }
                    types is NetworkState.Error -> {
                        UIState.Error(types.error.toString())
                    }
                    generations is NetworkState.Error -> {
                        UIState.Error(generations.error.toString())
                    }
                    else -> {
                        UIState.Error("")
                    }
                }
        }
    }
}

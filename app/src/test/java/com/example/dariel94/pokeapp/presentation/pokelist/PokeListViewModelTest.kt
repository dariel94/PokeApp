/*
 * *
 *  Created by dariel94 on ${DATE}.
 * /
 */

package com.example.dariel94.pokeapp.presentation.pokelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dariel25.android.pokeapp.data.network.NetworkState
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.domain.usecase.PokemonListUseCase
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.pokelist.PokeListViewModel
import com.example.dariel94.pokeapp.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import java.lang.Error

/**
 * Created by dariel94 on 2/11/2021.
 */
@ExperimentalCoroutinesApi
class PokeListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: PokemonListUseCase

    private lateinit var viewModel: PokeListViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = PokeListViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testFetchPokemonsSuccess() = runBlockingTest {
        coEvery { useCase.invoke() } returns NetworkState.Success(getMockedPokemonList())

        viewModel.fetchPokemons()

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Success<*>
        )
    }

    @Test
    fun testFetchPokemonsError() = runBlockingTest {
        coEvery { useCase.invoke() } returns NetworkState.Error(Error())

        viewModel.fetchPokemons()

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Error
        )
    }

    private fun getMockedPokemonList() : List<PokemonSimple> {
        val list = arrayListOf<PokemonSimple>()
        list.add(PokemonSimple("1", "test pokemon 1", "type2", "type2"))
        list.add(PokemonSimple("2", "test pokemon 2", "type2", "type2"))
        list.add(PokemonSimple("3", "test pokemon 3", "type2", "type2"))
        return list
    }
}
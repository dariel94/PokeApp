/*
 * *
 *  Created by dariel94 on ${DATE}.
 * /
 */

package com.example.dariel94.pokeapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dariel25.android.pokeapp.data.network.NetworkState
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.usecase.PokemonDetailUseCase
import com.dariel25.android.pokeapp.presentation.detail.PokemonDetailViewModel
import com.dariel25.android.pokeapp.presentation.model.UIState
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
import org.mockito.ArgumentMatchers.anyString
import java.lang.Error

/**
 * Created by dariel94 on 2/11/2021.
 */
@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: PokemonDetailUseCase

    private lateinit var viewModel: PokemonDetailViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = PokemonDetailViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testFetchPokemonSuccess() = runBlockingTest {
        coEvery { useCase.invoke(anyString()) } returns NetworkState.Success(getMockedPokemon())

        viewModel.fetchPokemon(anyString())

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Success<*>
        )
    }

    @Test
    fun testFetchPokemonError() = runBlockingTest {
        coEvery { useCase.invoke(anyString()) } returns NetworkState.Error(Error())

        viewModel.fetchPokemon(anyString())

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Error
        )
    }

    private fun getMockedPokemon() : Pokemon {
        return Pokemon("1", "test pokemon", 1, 1, listOf(), listOf(), listOf())
    }
}
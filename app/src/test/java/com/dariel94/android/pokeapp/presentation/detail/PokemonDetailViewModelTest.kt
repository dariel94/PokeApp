package com.dariel94.android.pokeapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.EvolutionChain
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.domain.usecase.PokemonUseCase
import com.dariel94.android.pokeapp.presentation.model.UIState
import com.dariel94.android.pokeapp.utils.getOrAwaitValue
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
    private lateinit var useCase: PokemonUseCase

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
        coEvery { useCase.invoke(anyString(), anyString()) } returns NetworkState.Success(getMockedPokemon())

        viewModel.fetchPokemon(anyString(), anyString())

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Success<*>
        )
    }

    @Test
    fun testFetchPokemonError() = runBlockingTest {
        coEvery { useCase.invoke(anyString(), anyString()) } returns NetworkState.Error(Error())

        viewModel.fetchPokemon(anyString(), anyString())

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Error
        )
    }

    private fun getMockedPokemon() : Pokemon {
        return Pokemon("1", "test pokemon", "test specie","desc", 1, 1, listOf(),
            listOf(), listOf(), EvolutionChain("", "", null, listOf()),
            isLegendary = false,
            isBaby = false,
            isMythical = false,
            baseExperience = 1,
            eggGroups = listOf(),
            growthRate = "",
            genderRate = 1,
            captureRate = 1,
            baseHappiness = 1,
            hatchCounter = 1,
            generation = "gen",
            habitat = "",
            isFavorite = false,
            varieties = listOf(),
        )
    }
}
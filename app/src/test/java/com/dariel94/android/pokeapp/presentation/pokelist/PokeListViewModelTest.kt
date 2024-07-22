package com.dariel94.android.pokeapp.presentation.pokelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dariel94.android.pokeapp.domain.NetworkState
import com.dariel94.android.pokeapp.domain.model.PokemonSimple
import com.dariel94.android.pokeapp.domain.usecase.FavoritesUseCase
import com.dariel94.android.pokeapp.domain.usecase.GenerationsUseCase
import com.dariel94.android.pokeapp.domain.usecase.PokemonListUseCase
import com.dariel94.android.pokeapp.domain.usecase.TypesUseCase
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
import java.lang.Error

/**
 * Created by dariel94 on 2/11/2021.
 */
@ExperimentalCoroutinesApi
class PokeListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var pokemonListUseCase: PokemonListUseCase

    @MockK
    private lateinit var typesUseCase: TypesUseCase

    @MockK
    private lateinit var favoritesUseCase: FavoritesUseCase

    @MockK
    private lateinit var generationsUseCase: GenerationsUseCase

    private lateinit var viewModel: PokeListViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
        viewModel = PokeListViewModel(pokemonListUseCase, typesUseCase, generationsUseCase, favoritesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testFetchPokemonsSuccess() = runBlockingTest {
        coEvery { pokemonListUseCase.invoke() } returns NetworkState.Success(getMockedPokemonList())
        coEvery { typesUseCase.invoke() } returns NetworkState.Success(emptyList())
        coEvery { generationsUseCase.invoke() } returns NetworkState.Success(emptyList())
        coEvery { favoritesUseCase.invoke() } returns NetworkState.Success(emptyList())

        viewModel.fetchPokemonListData()

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Success<*>
        )
    }

    @Test
    fun testFetchPokemonsError() = runBlockingTest {
        coEvery { pokemonListUseCase.invoke() } returns NetworkState.Error(Error())
        coEvery { typesUseCase.invoke() } returns NetworkState.Success(emptyList())
        coEvery { generationsUseCase.invoke() } returns NetworkState.Success(emptyList())
        coEvery { favoritesUseCase.invoke() } returns NetworkState.Success(emptyList())

        viewModel.fetchPokemonListData()

        Assert.assertTrue(
            viewModel.getViewStateLiveData().getOrAwaitValue() is UIState.Error
        )
    }

    private fun getMockedPokemonList() : List<PokemonSimple> {
        val list = arrayListOf<PokemonSimple>()
        list.add(PokemonSimple("1", "test pokemon 1", "type2", "type2", 1))
        list.add(PokemonSimple("2", "test pokemon 2", "type2", "type2", 1))
        list.add(PokemonSimple("3", "test pokemon 3", "type2", "type2", 1))
        return list
    }
}

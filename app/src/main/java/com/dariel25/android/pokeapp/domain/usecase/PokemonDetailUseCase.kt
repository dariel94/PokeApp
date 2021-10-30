package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.data.network.NetworkState
import com.dariel25.android.pokeapp.domain.mapper.PokemonMapper
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.repository.PokeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val repository: PokeApiRepository,
    private val mapper: PokemonMapper
) {
    suspend fun getPokemon(id: String) : NetworkState<Pokemon> = try {
        val pokemonDto = withContext(Dispatchers.IO) {
            repository.getPokemon(id)
        }
        NetworkState.Success(mapper.mapDtoToUI(pokemonDto))
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}

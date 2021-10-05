package com.dariel25.android.pokeapp.domain.usecase

import com.dariel25.android.pokeapp.domain.mapper.PokemonMapper
import com.dariel25.android.pokeapp.data.network.model.NetworkState
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
        val pokemon = withContext(Dispatchers.IO) {
            val pokemonDto = repository.getPokemon(id)
            val pokemonSpeciesDto = repository.getPokemonSpecies(id)
            val chainId = mapper.getIdFromUrl(pokemonSpeciesDto.evolutionChain.url)
            val evolutionChainDto = repository.getEvolutionChain(chainId)

            mapper.mapDtoToUI(pokemonDto, pokemonSpeciesDto, evolutionChainDto)
        }
        NetworkState.Success(pokemon)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}

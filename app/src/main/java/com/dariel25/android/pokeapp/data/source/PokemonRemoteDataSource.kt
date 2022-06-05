package com.dariel25.android.pokeapp.data.source

import com.dariel25.android.pokeapp.data.api.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.utils.StringUtils
import com.dariel25.android.pokeapp.data.mapper.mapToDomain
import com.dariel25.android.pokeapp.domain.model.Pokemon
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokeApi: PokeApi
) {
    suspend fun getPokemon(id: String): Pokemon {
        val pokemonDto = pokeApi.getPokemon(id)
        val pokemonSpeciesDto = pokeApi.getPokemonSpecies(id)
        val pokemonChainId = StringUtils.getIdFromUrl(pokemonSpeciesDto.evolutionChain.url)
        val evolutionChainDto = pokeApi.getEvolutionChain(pokemonChainId)

        val abilitiesDto = pokemonDto.abilities.map {
            pokeApi.getAbility(it.ability.name)
        }

        return pokemonDto.mapToDomain(pokemonSpeciesDto, evolutionChainDto, abilitiesDto)
    }
}

package com.dariel25.android.pokeapp.data.repository

import com.dariel25.android.pokeapp.data.network.pokeapi.PokeApi
import com.dariel25.android.pokeapp.data.network.pokeapi.mapper.PokemonMapper
import com.dariel25.android.pokeapp.data.room.PokemonDao
import com.dariel25.android.pokeapp.data.room.model.mapToDomain
import com.dariel25.android.pokeapp.data.utils.StringUtils
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.repository.PokeApiRepository
import javax.inject.Inject

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokeApiRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val dao: PokemonDao
) : PokeApiRepository {

    override suspend fun getPokemon(id: String): Pokemon {
        val cachedPokemon = dao.getById(id)

        return if (cachedPokemon == null) {
            val pokemonDto = api.getPokemon(id)
            val pokemonSpeciesDto = api.getPokemonSpecies(id)
            val pokemonChainId = StringUtils.getIdFromUrl(pokemonSpeciesDto.evolutionChain.url)
            val evolutionChainDto = api.getEvolutionChain(pokemonChainId)

            val pokemon = PokemonMapper.mapToDomain(pokemonDto, pokemonSpeciesDto, evolutionChainDto)

            dao.insert(pokemon.mapToEntity())

            pokemon
        } else {
            cachedPokemon.mapToDomain()
        }
    }
}

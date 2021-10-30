package com.dariel25.android.pokeapp.domain.mapper

import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.domain.utils.PokemonUtils
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun mapDtoToUI(
        pokemonDto: PokemonDto
    ): Pokemon {

        val color = if (pokemonDto.types.isNotEmpty()) {
            PokemonUtils.getPokemonTypeColor(pokemonDto.types.first().type.name)
        } else {
            PokemonUtils.getPokemonTypeColor("")
        }

        val types = pokemonDto.types.map {
            it.type.name
        }

        val abilities = pokemonDto.abilities.map {
            it.ability.name
        }

        val stats = pokemonDto.stats.map {
            Stat(PokemonUtils.getStatName(it.stat.name), it.baseStat)
        }

        return Pokemon(
            pokemonDto.id,
            pokemonDto.name,
            PokemonUtils.getImageUrl(pokemonDto.id),
            PokemonUtils.getHeightInMeters(pokemonDto.height),
            PokemonUtils.getWeightInKilograms(pokemonDto.weight),
            color,
            types,
            stats,
            abilities)
    }
}

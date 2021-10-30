package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.network.pokelist.model.PokemonSimpleDto
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.domain.utils.PokemonUtils
import javax.inject.Inject

class PokemonSimpleMapper @Inject constructor() {

    fun mapDtoToUI(type: List<PokemonSimpleDto>): List<SimplePokemon> {
        return type.map {
            SimplePokemon(
                id = it.id,
                name = it.name,
                imageUrl = PokemonUtils.getImageUrl(it.id),
                PokemonUtils.getPokemonTypeColor(it.type1),
                type1 = it.type1,
                type2 = it.type2,
                legendary = it.legendary
            )
        }
    }

    fun mapEntityToUI(type: List<PokemonSimpleEntity>): List<SimplePokemon> {
        return type.map {
            SimplePokemon(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                PokemonUtils.getPokemonTypeColor(it.type1),
                type1 = it.type1,
                type2 = it.type2,
                legendary = it.legendary
            )
        }
    }

    fun mapDtoToEntity(type: List<PokemonSimpleDto>): List<PokemonSimpleEntity> {
        return type.map {
            PokemonSimpleEntity(
                id = it.id,
                name = it.name,
                imageUrl = PokemonUtils.getImageUrl(it.id),
                PokemonUtils.getPokemonTypeColor(it.type1),
                type1 = it.type1,
                type2 = it.type2,
                legendary = it.legendary
            )
        }
    }
}
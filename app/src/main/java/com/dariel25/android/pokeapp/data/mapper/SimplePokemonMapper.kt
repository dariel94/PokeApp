package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.network.model.Resource
import com.dariel25.android.pokeapp.data.network.model.SimplePokemonDto
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import javax.inject.Inject

class SimplePokemonMapper @Inject constructor() {

    fun mapDtoToUI(type: List<Resource>): List<SimplePokemon> {
        return type.map {
            SimplePokemon(
                id = getPokemonId(it.url),
                name = it.name
            )
        }
    }

    fun mapEntityToUI(type: List<PokemonSimpleEntity>): List<SimplePokemon> {
        return type.map {
            SimplePokemon(
                id = it.id,
                name = it.name,
                type1 = it.type1,
                type2 = it.type2
            )
        }
    }

    fun mapDtoToUI(dto: SimplePokemonDto): SimplePokemon {
        return SimplePokemon(
            id = dto.id,
            name = dto.name,
            type1 = dto.types?.get(0)?.type?.name,
            type2 = dto.types?.get(1)?.type?.name,
        )
    }

    fun mapDtoToEntity(type: List<Resource>): List<PokemonSimpleEntity> {
        return type.map {
            PokemonSimpleEntity(
                id = getPokemonId(it.url),
                name = it.name
            )
        }
    }

    private fun getPokemonId(url: String) : String {
        val urlSplit = url.split('/')
        return urlSplit[urlSplit.size - 2]
    }
}
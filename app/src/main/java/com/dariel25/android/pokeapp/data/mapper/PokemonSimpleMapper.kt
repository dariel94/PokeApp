package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.network.model.PokemonSimpleDto
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import javax.inject.Inject

class PokemonSimpleMapper @Inject constructor() {

    fun mapDtoToUI(type: List<PokemonSimpleDto>): List<PokemonSimple> {
        return type.map {
            PokemonSimple(
                id = it.id,
                name = it.name,
                type1 = it.type1,
                type2 = it.type2,
                color = it.color,
                legendary = it.legendary
            )
        }
    }

    fun mapEntityToUI(type: List<PokemonSimpleEntity>): List<PokemonSimple> {
        return type.map {
            PokemonSimple(
                id = it.id,
                name = it.name,
                type1 = it.type1,
                type2 = it.type2,
                color = it.color,
                legendary = it.legendary
            )
        }
    }

    fun mapDtoToEntity(type: List<PokemonSimpleDto>): List<PokemonSimpleEntity> {
        return type.map {
            PokemonSimpleEntity(
                id = it.id,
                name = it.name,
                type1 = it.type1,
                type2 = it.type2,
                color = it.color,
                legendary = it.legendary,
                height = it.height,
                weight = it.weight,
                hp = it.hp,
                atk = it.atk,
                def = it.def,
                spAtk = it.spAtk,
                spDef = it.spDef,
                spd = it.spd,
                total = it.total
            )
        }
    }
}
package com.dariel25.android.pokeapp.data.mapper

import com.dariel25.android.pokeapp.data.network.github.model.SimplePokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonDto
import com.dariel25.android.pokeapp.data.network.pokeapi.model.PokemonSpeciesDto
import com.dariel25.android.pokeapp.data.room.model.PokemonSimpleEntity
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun mapDtoToUI(p: PokemonDto, s: PokemonSpeciesDto): Pokemon {
        val types = p.types.map {
            it.type.name
        }
        val abilities = p.abilities.map {
            it.ability.name
        }

        s.evolutionChain.


        return Pokemon(p.id, p.name, s.color, p.height, p.weight, s.generation.name, s.isLegendary, types, abilities, types)
    }

    fun mapEntityToUI(type: List<PokemonSimpleEntity>): List<SimplePokemon> {
        return type.map {
            SimplePokemon(
                id = it.id,
                name = it.name,
                type1 = it.type1,
                type2 = it.type2,
                color = it.color,
                legendary = it.legendary
            )
        }
    }

    fun mapDtoToEntity(type: List<SimplePokemonDto>): List<PokemonSimpleEntity> {
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
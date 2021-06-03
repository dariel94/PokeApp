package com.dariel25.android.pokeapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonSimpleEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "type1") val type1: String = "",
    @ColumnInfo(name = "type2") val type2: String = "",
    @ColumnInfo(name = "color") val color: String = "",
    @ColumnInfo(name = "legendary") val legendary: Boolean = false,
    @ColumnInfo(name = "height") val height: Float = 0F,
    @ColumnInfo(name = "weight") val weight: Float = 0F,
    @ColumnInfo(name = "hp") val hp: Float = 0F,
    @ColumnInfo(name = "atk") val atk: Float = 0F,
    @ColumnInfo(name = "def") val def: Float = 0F,
    @ColumnInfo(name = "sp_atk") val spAtk: Float = 0F,
    @ColumnInfo(name = "sp_def") val spDef: Float = 0F,
    @ColumnInfo(name = "spd") val spd: Float = 0F,
    @ColumnInfo(name = "total") val total: Float = 0F
)

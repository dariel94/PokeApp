package com.dariel25.android.pokeapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PokemonSimpleDto(
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val color: String = "",
    val legendary: Boolean = false,
    val height: Float = 0F,
    val weight: Float = 0F,
    val hp: Float = 0F,
    val atk: Float = 0F,
    val def: Float = 0F,
    @SerializedName("sp_atk")  val spAtk: Float = 0F,
    @SerializedName("sp_def") val spDef: Float = 0F,
    val spd: Float = 0F,
    val total: Float = 0F
)

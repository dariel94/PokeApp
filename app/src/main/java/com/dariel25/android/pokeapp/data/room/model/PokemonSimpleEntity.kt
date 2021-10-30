package com.dariel25.android.pokeapp.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonSimpleEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "image_url") val imageUrl: String = "",
    @ColumnInfo(name = "color") val color: Int = 0,
    @ColumnInfo(name = "type1") val type1: String = "",
    @ColumnInfo(name = "type2") val type2: String = "",
    @ColumnInfo(name = "legendary") val legendary: Boolean = false
)

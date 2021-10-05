package com.dariel25.android.pokeapp.presentation.utils

object StringUtils {
    const val SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{id}.png"

    fun getIdTitle(id: String): String {
        var idTitle = "#"
        val zeros = 3 - id.length
        repeat (zeros) {
            idTitle += "0"
        }
        return idTitle + id
    }
}
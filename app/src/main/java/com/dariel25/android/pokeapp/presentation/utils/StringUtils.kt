package com.dariel25.android.pokeapp.presentation.utils

object StringUtils {
    fun getIdTitle(id: String): String {
        var idTitle = "#"
        val zeros = 3 - id.length
        repeat (zeros) {
            idTitle += "0"
        }
        return idTitle + id
    }
}
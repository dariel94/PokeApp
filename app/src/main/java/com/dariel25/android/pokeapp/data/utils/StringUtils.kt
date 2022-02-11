package com.dariel25.android.pokeapp.data.utils

/**
 * Created by dariel94 on 11/1/2022.
 */
object StringUtils {
    fun getIdFromUrl(url: String): String {
        val splitUrl = url.split('/')
        return splitUrl[splitUrl.size - 2]
    }
}
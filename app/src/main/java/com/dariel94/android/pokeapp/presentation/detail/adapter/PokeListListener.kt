/*
 * Created by dariel94 on 21/7/24 5:21
 * Last modified 21/7/24 5:21
 */

package com.dariel94.android.pokeapp.presentation.detail.adapter

interface PokeListListener {
    fun onPokemonClicked(id: String)
    fun onEmptyList()
}
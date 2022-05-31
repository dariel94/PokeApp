/*
 * Created by dariel94 on 30/5/22 13:44
 * Last modified 30/5/22 13:44
 */

package com.dariel25.android.pokeapp.presentation.model

data class PokeListData(
    val pokemons: List<PokemonSimpleUI>?,
    val types: List<String>?,
    val generations: List<String>?,
    val categories: List<String>?
)

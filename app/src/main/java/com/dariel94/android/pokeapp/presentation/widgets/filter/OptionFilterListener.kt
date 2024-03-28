/*
 * Created by dariel94 on 30/5/22 23:01
 * Last modified 30/5/22 22:59
 */

package com.dariel94.android.pokeapp.presentation.widgets.filter

interface OptionFilterListener {

    fun onFilterData(types: List<String>, gens: List<String>, cats: List<String>)
}
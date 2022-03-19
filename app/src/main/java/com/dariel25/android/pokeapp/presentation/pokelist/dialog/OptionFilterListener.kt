/*
 * Created by dariel94 on 8/3/22 06:35
 * Last modified 8/3/22 06:35
 */

package com.dariel25.android.pokeapp.presentation.pokelist.dialog

import com.dariel25.android.pokeapp.presentation.model.OptionFilters

interface OptionFilterListener {

    fun setFilterData(optionFilters: OptionFilters)
}
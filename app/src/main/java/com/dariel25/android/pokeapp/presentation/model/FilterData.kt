/*
 * Created by dariel94 on 8/3/22 06:57
 * Last modified 8/3/22 06:57
 */

package com.dariel25.android.pokeapp.presentation.model

data class FilterData(
    val types: List<FilterOption>,
    val generations: List<FilterOption>,
    val categories: List<FilterOption>
)

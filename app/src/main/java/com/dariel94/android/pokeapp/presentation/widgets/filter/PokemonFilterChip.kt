/*
 * Created by dariel94 on 19/7/24 6:50
 * Last modified 19/7/24 6:50
 */

package com.dariel94.android.pokeapp.presentation.widgets.filter

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.chip.Chip
class PokemonFilterChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Chip(context, attrs, defStyleAttr) {
    var filterId: String? = null
}
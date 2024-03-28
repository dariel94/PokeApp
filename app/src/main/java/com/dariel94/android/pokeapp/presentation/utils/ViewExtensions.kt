/*
 * Created by David on 18/2/22 21:46
 */

package com.dariel94.android.pokeapp.presentation.utils

import android.view.View

fun View.show(): View {
    visibility = View.VISIBLE
    return this
}

fun View.hide(): View {
    visibility = View.GONE
    return this
}

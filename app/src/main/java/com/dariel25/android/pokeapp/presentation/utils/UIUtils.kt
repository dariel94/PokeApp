package com.dariel25.android.pokeapp.presentation.utils

import android.content.Context
import android.graphics.Color
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

/**
 * Created by dariel94 on 31/10/2021.
 */
object UIUtils {
    fun getLoadingPlaceholder(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 3f
        circularProgressDrawable.centerRadius = 20f
        circularProgressDrawable.setColorSchemeColors(Color.WHITE)
        circularProgressDrawable.start()

        return circularProgressDrawable
    }
}
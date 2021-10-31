package com.dariel25.android.pokeapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
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

    fun changeStatusBarColor(activity: Activity, color: Int) {
        val window: Window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, color)
    }
}
/*
 * Created by dariel94 on 18/7/24 16:06
 * Last modified 17/7/24 20:37
 */

package com.dariel94.android.pokeapp.presentation.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import com.dariel94.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel94.android.pokeapp.presentation.pokelist.PokeListActivity
import java.util.Locale

object LanguageUtils {
    fun getLanguage(context: Context): String {
        val allowed = arrayOf("en", "es")
        val lan = context.resources.configuration.locale.language
        if (allowed.contains(lan)) {
            return lan
        }
        return "en"
    }

    fun setLocale(lang: String?, activity: BaseActivity) {
        val myLocale = Locale(lang)
        val res = activity.resources
        val dm = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(activity, PokeListActivity::class.java)
        activity.startActivity(refresh)
        activity.finish()
    }
}
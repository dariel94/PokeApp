package com.dariel94.android.pokeapp.presentation.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.domain.model.Stat

/**
 * Created by David on 5/10/2021.
 */
class StatWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val statName: TextView
    private val statValue: TextView
    private val statBar: ProgressBar
    private var stat: Stat? = null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.pokeapp_stat_widget_layout, this)
        statName = findViewById(R.id.stat_name)
        statValue = findViewById(R.id.stat_value)
        statBar = findViewById(R.id.stat_bar)
    }

    fun setStat(stat: Stat, color: Int) {
        this.stat = stat
        statName.text = stat.name
        var value = stat.value.toString()
        while (value.length < 3) {
            value = "0$value"
        }
        statValue.text = value
        statName.setTextColor(ContextCompat.getColor(context, color))
        statBar.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        animateStat()
    }

    private fun animateStat() {
        stat?.let {
            ObjectAnimator.ofInt(statBar, "progress", it.value)
                .setDuration(1000)
                .start()
        }
    }
}
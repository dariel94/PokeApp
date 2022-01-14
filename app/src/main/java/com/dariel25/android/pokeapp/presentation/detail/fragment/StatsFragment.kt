package com.dariel25.android.pokeapp.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.domain.model.Stat
import com.dariel25.android.pokeapp.presentation.widgets.StatWidget

/**
 * Created by dariel94 on 9/1/2022.
 */
class StatsFragment(
    private val stats: List<Stat>,
    @ColorRes private val color: Int
): Fragment() {

    private lateinit var statsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_stats, container, false)
        statsContainer = view.findViewById(R.id.stats_container)
        for (stat in stats) {
            val statWidget = StatWidget(view.context)
            statWidget.setStat(stat, color)
            statsContainer.addView(statWidget)
        }
        return view
    }
}
/*
 * Created by dariel94 on 24/2/22 18:19
 * Last modified 24/2/22 18:19
 */

package com.dariel94.android.pokeapp.presentation.detail.fragment.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.presentation.model.PokemonUI
import com.dariel94.android.pokeapp.presentation.widgets.StatWidget

class StatsFragment(
    private val pokemon: PokemonUI
): Fragment() {

    private lateinit var statsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_stats, container, false)
        statsContainer = view.findViewById(R.id.container)

        view.findViewById<TextView>(R.id.stat_height).text = pokemon.height
        view.findViewById<TextView>(R.id.stat_weight).text = pokemon.weight

        for (stat in pokemon.stats) {
            val statWidget = StatWidget(view.context)
            statWidget.setStat(stat, pokemon.color)
            statsContainer.addView(statWidget)
        }

        view.findViewById<TextView>(R.id.genderRate).text = pokemon.genderRate
        view.findViewById<TextView>(R.id.eggGroups).text = pokemon.eggGroups
        view.findViewById<TextView>(R.id.catchRate).text = pokemon.captureRate
        view.findViewById<TextView>(R.id.baseHappiness).text = pokemon.baseHappiness
        view.findViewById<TextView>(R.id.baseExperience).text = pokemon.baseExperience
        view.findViewById<TextView>(R.id.growthRate).text = pokemon.growthRate
        view.findViewById<TextView>(R.id.eggCycles).text = pokemon.hatchCounter

        context?.let {
            val colorDrawable = ContextCompat.getColor(it, pokemon.color)
            view.findViewById<TextView>(R.id.genderRateText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.eggGroupsText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.eggCyclesText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.baseExperienceText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.baseHappinessText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.growthRateText).setTextColor(colorDrawable)
            view.findViewById<TextView>(R.id.catchRateText).setTextColor(colorDrawable)
        }

        return view
    }
}
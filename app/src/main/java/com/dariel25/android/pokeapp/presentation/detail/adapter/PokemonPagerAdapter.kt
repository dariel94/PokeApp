package com.dariel25.android.pokeapp.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dariel25.android.pokeapp.presentation.detail.fragment.evolutions.EvolutionsFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.moves.MovesFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.stats.StatsFragment
import com.dariel25.android.pokeapp.presentation.model.PokemonUI

/**
 * Created by dariel94 on 9/1/2022.
 */
class PokemonPagerAdapter(
    fa: FragmentActivity,
    private val pokemonUI: PokemonUI
): FragmentStateAdapter(fa) {

    private val hasEvolutionTab: Boolean = pokemonUI.evolutions.isNullOrEmpty()

    val tabTitles: Array<String> = if (hasEvolutionTab) {
        arrayOf(
            "     Stats     ",
            "    Abilities    "
        )
    } else {
        arrayOf(
            "     Stats     ",
            " Evolutions ",
            "    Abilities    "
        )
    }

    override fun getItemCount(): Int = if (hasEvolutionTab) 2 else 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                StatsFragment(pokemonUI)
            }
            1 -> {
                if (hasEvolutionTab) {
                    MovesFragment(pokemonUI.abilities)
                } else {
                    EvolutionsFragment(pokemonUI.evolutions)
                }
            }
            else -> {
                MovesFragment(pokemonUI.abilities)
            }
        }
    }
}
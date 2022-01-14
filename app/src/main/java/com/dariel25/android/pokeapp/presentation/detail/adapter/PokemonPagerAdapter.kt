package com.dariel25.android.pokeapp.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dariel25.android.pokeapp.presentation.detail.fragment.EvolutionsFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.MovesFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.StatsFragment
import com.dariel25.android.pokeapp.presentation.model.PokemonUI

/**
 * Created by dariel94 on 9/1/2022.
 */
class PokemonPagerAdapter(
    fa: FragmentActivity,
    private val pokemonUI: PokemonUI
): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                StatsFragment(pokemonUI.stats, pokemonUI.color)
            }
            1 -> {
                EvolutionsFragment(pokemonUI.abilities)
            }
            else -> {
                MovesFragment(pokemonUI.abilities)
            }
        }
    }
}
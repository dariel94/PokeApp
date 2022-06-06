package com.dariel25.android.pokeapp.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dariel25.android.pokeapp.presentation.detail.fragment.evolutions.EvolutionsFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.abilities.AbilitiesFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.stats.StatsFragment
import com.dariel25.android.pokeapp.presentation.detail.fragment.varieties.VarietiesFragment
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import java.util.ArrayList

/**
 * Created by dariel94 on 9/1/2022.
 */
class PokemonPagerAdapter(
    fa: FragmentActivity,
    pokemonUI: PokemonUI
): FragmentStateAdapter(fa) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()
    val tabTitles: ArrayList<String> = arrayListOf()

    init {
        fragmentList.add(StatsFragment(pokemonUI))
        tabTitles.add("  Stats  ")

        if (pokemonUI.evolutions.isNotEmpty()) {
            fragmentList.add(EvolutionsFragment(pokemonUI.evolutions))
            tabTitles.add(" Evolutions ")
        }

        if (pokemonUI.varieties.isNotEmpty()) {
            fragmentList.add(VarietiesFragment(pokemonUI.varieties))
            tabTitles.add(" Forms ")
        }

        fragmentList.add(AbilitiesFragment(pokemonUI.abilities))
        tabTitles.add(" Abilities ")
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}

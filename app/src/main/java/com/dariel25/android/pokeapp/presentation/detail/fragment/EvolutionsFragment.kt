package com.dariel25.android.pokeapp.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.domain.model.EvolutionChain
import com.dariel25.android.pokeapp.presentation.widgets.EvolutionWidget

/**
 * Created by dariel94 on 9/1/2022.
 */
class EvolutionsFragment(
    private val evolutionChain: EvolutionChain
): Fragment() {

    private lateinit var evolutionsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_base, container, false)
        evolutionsContainer = view.findViewById(R.id.container)

        checkEvolution(evolutionChain)

        return view
    }

    private fun checkEvolution(evolutionChain: EvolutionChain) {
        for (evolve in evolutionChain.evolvesTo) {
            val evolutionWidget = EvolutionWidget(evolutionsContainer.context)
            evolutionWidget.setData(
                evolutionChain.id,
                evolutionChain.name,
                evolve.id,
                evolve.name,
                evolve.minLevel
            )
            evolutionsContainer.addView(evolutionWidget)
            checkEvolution(evolve)
        }
    }
}

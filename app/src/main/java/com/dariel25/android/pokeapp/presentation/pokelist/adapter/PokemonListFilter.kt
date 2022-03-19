package com.dariel25.android.pokeapp.presentation.pokelist.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import com.dariel25.android.pokeapp.presentation.model.OptionFilters
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListFilter(
    private val adapter: PokeListAdapter
): Filter() {

    var list = adapter.dataset

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterString = constraint.toString()
        val results = FilterResults()

        val filteredList: List<PokemonSimpleUI> = if (filterString.isEmpty()) {
            list
        } else {
            list.filter {
                it.name.contains(filterString, true) or (it.id == filterString)
            }
        }

        results.values = filteredList
        results.count = filteredList.size

        return results
    }

    @SuppressLint("NotifyDataSetChanged")
    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredDataset = results?.values as List<PokemonSimpleUI>
        adapter.notifyDataSetChanged()
    }

    fun setOptionFilters(optionFilters: OptionFilters) {
        list = ArrayList(adapter.dataset)

        if (optionFilters.generations.isNotEmpty()) {
            list = list.filter {
                optionFilters.generations.contains(it.generation)
            }
        }

        if (optionFilters.types.isNotEmpty()) {
            list = list.filter {
                optionFilters.types.contains(it.type1) || optionFilters.types.contains(it.type2)
            }
        }
    }

}

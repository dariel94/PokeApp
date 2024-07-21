package com.dariel94.android.pokeapp.presentation.pokelist.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import com.dariel94.android.pokeapp.presentation.model.PokemonSimpleUI

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokemonListFilter(
    private val adapter: PokeListAdapter,
): Filter() {

    var list = emptyList<PokemonSimpleUI>()
    var favoritesList: List<String>? = null

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
        results?.values?.let {
            adapter.filteredDataset = it as List<PokemonSimpleUI>
            adapter.notifyDataSetChanged()
        }
    }

    fun setOptionFilters(types: List<String>, gens: List<String>, cats: List<String>) {
        list = ArrayList(adapter.dataset)

        if (gens.isNotEmpty()) {
            list = list.filter {
                gens.contains(it.generation)
            }
        }

        if (types.isNotEmpty()) {
            list = list.filter {
                types.contains(it.type1.lowercase()) || types.contains(it.type2.lowercase())
            }
        }

        if (cats.isNotEmpty()) {
            if (cats.contains("legendary")) {
                list = list.filter {
                    it.legendary
                }
            }
            if (cats.contains("favourite")) {
                list = list.filter {
                    favoritesList?.contains(it.id) ?: false
                }
            }
        }
    }

}

package com.dariel25.android.pokeapp.presentation.pokelist.adapter

import android.widget.Filter
import com.dariel25.android.pokeapp.domain.model.PokemonSimple

class PokemonListFilter(
    private val adapter: PokeListAdapter
): Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterString = constraint.toString()
        val results = FilterResults()
        val list = adapter.dataset

        val filteredList: List<PokemonSimple> = if (filterString.isEmpty()) {
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

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredDataset = results?.values as List<PokemonSimple>
        adapter.notifyDataSetChanged()
    }

}

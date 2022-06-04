package com.dariel25.android.pokeapp.presentation.pokelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.databinding.PokeappActivityPokelistBinding
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.PokeListData
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.pokelist.adapter.PokeListAdapter
import com.dariel25.android.pokeapp.presentation.utils.hide
import com.dariel25.android.pokeapp.presentation.utils.show
import com.dariel25.android.pokeapp.presentation.widgets.filter.FilterBottomSheet
import com.dariel25.android.pokeapp.presentation.widgets.filter.OptionFilterListener
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokeListActivity : BaseActivity(), OptionFilterListener {

    private val pokeListViewModel by viewModels<PokeListViewModel>()
    private var pokeListAdapter: PokeListAdapter = PokeListAdapter(this)
    private lateinit var searchView: SearchView
    private val filterBottomSheet: FilterBottomSheet by lazy {
        FilterBottomSheet(this)
    }
    private val binding: PokeappActivityPokelistBinding by lazy {
        PokeappActivityPokelistBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = pokeListAdapter
        filterBottomSheet.listener = this

        setListAnimation()

        binding.clearFilterView.setOnClickListener {
            clearFilters()
        }

        pokeListViewModel.getViewStateLiveData().observe(this) {
            updateViewStatus(it)
        }
        pokeListViewModel.fetchPokemonListData()

    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokeListViewModel.fetchPokemonListData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pokeapp_options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search by name or id"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                pokeListAdapter.filter.filter(newText)
                return false
            }
        })
        val filterItem = menu.findItem(R.id.filter)
        filterItem.setOnMenuItemClickListener {
            if (!filterBottomSheet.isShowing) {
                filterBottomSheet.show()
            }
            false
        }
        return true
    }

    private fun updateViewStatus(networkState: UIState<PokeListData>) {
        when (networkState) {
            is UIState.Loading -> showLoadingView()
            is UIState.Success -> loadPokemonListData(networkState.data)
            is UIState.Error -> showErrorView(networkState.message)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadPokemonListData(pokemonListData: PokeListData) {
        pokemonListData.pokemons?.let {
            pokeListAdapter.dataset = it
            pokeListAdapter.notifyDataSetChanged()
        }
        showLayoutView()
        filterBottomSheet.setFilterData(
            pokemonListData.types ?: emptyList(),
            pokemonListData.generations ?: emptyList(),
            pokemonListData.categories ?: emptyList()
        )
    }

    private fun setListAnimation() {
        val resId = R.anim.pokeapp_layout_slide_from_bottom
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        binding.recyclerView.layoutAnimation = animation
    }

    override fun onFilterData(types: List<String>, gens: List<String>, cats: List<String>) {
        if (types.isEmpty() && gens.isEmpty() && cats.isEmpty()) {
            binding.clearFilterView.hide()
        } else {
            binding.clearFilterView.show()
        }
        pokeListAdapter.filter.setOptionFilters(types, gens, cats)
        pokeListAdapter.filter.filter(searchView.query)
    }

    private fun clearFilters() {
        onFilterData(emptyList(), emptyList(), emptyList())
        filterBottomSheet.clearSelections()
    }
}

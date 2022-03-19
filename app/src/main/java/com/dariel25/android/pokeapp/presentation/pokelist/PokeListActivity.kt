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
import com.dariel25.android.pokeapp.presentation.model.OptionFilters
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.pokelist.adapter.PokeListAdapter
import com.dariel25.android.pokeapp.presentation.pokelist.dialog.FilterDialog
import com.dariel25.android.pokeapp.presentation.pokelist.dialog.FilterDataViewModel
import com.dariel25.android.pokeapp.presentation.pokelist.dialog.OptionFilterListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokeListActivity : BaseActivity(), OptionFilterListener {

    private val pokeListViewModel by viewModels<PokeListViewModel>()
    private val filterDataViewModel by viewModels<FilterDataViewModel>()
    private var pokeListAdapter: PokeListAdapter = PokeListAdapter(this)
    private val filterDialog = FilterDialog()
    private lateinit var searchView: SearchView
    private val binding: PokeappActivityPokelistBinding by lazy {
        PokeappActivityPokelistBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = pokeListAdapter
        filterDialog.listener = this
        setListAnimation()
        binding.swipeRefresh.setOnRefreshListener {
            pokeListViewModel.fetchPokemons()
        }

        pokeListViewModel.getViewStateLiveData()
            .observe(this) { updateViewStatus(it) }
        pokeListViewModel.fetchPokemons()

        filterDataViewModel.getViewStateLiveData().observe(this) {
            if (it is UIState.Success) {
                filterDialog.optionFilters = it.data
            }
        }
        filterDataViewModel.fetchOptionFilters()
    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokeListViewModel.fetchPokemons()
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
            filterDialog.show(supportFragmentManager, "")
            false
        }
        return true
    }

    private fun updateViewStatus(networkState: UIState<List<PokemonSimpleUI>?>) {
        when (networkState) {
            is UIState.Loading -> showLoadingView()
            is UIState.Success -> loadList(networkState.data)
            is UIState.Error -> showErrorView(networkState.message)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadList(list: List<PokemonSimpleUI>?) {
        list?.let {
            pokeListAdapter.dataset = it
            pokeListAdapter.notifyDataSetChanged()
        }
        showLayoutView()
        binding.swipeRefresh.isRefreshing = false
    }

    private fun setListAnimation() {
        val resId = R.anim.pokeapp_layout_slide_from_bottom
        val animation = AnimationUtils.loadLayoutAnimation(this, resId)
        binding.recyclerView.layoutAnimation = animation
    }

    override fun setFilterData(optionFilters: OptionFilters) {
        pokeListAdapter.filter.setOptionFilters(optionFilters)
        pokeListAdapter.filter.filter(searchView.query)
    }
}

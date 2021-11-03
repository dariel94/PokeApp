package com.dariel25.android.pokeapp.presentation.pokelist

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
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.pokelist.adapter.PokeListAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokeListActivity : BaseActivity() {

    private val pokeListViewModel by viewModels<PokeListViewModel>()
    private var pokeListAdapter: PokeListAdapter = PokeListAdapter(this)
    private val binding: PokeappActivityPokelistBinding by lazy {
        PokeappActivityPokelistBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = pokeListAdapter
        setListAnimation()

        pokeListViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        binding.swipeRefresh.setOnRefreshListener {
            pokeListViewModel.fetchPokemons()
        }
        pokeListViewModel.fetchPokemons()
    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokeListViewModel.fetchPokemons()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pokeapp_options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
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
        return true
    }

    private fun updateViewStatus(networkState: UIState<List<PokemonSimpleUI>?>) {
        when (networkState) {
            is UIState.Loading -> showLoadingView()
            is UIState.Success -> loadList(networkState.data)
            is UIState.Error -> showErrorView(networkState.message)
        }
    }

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
}

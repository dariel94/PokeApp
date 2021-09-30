package com.dariel25.android.pokeapp.presentation.pokelist

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.databinding.ActivityPokelistBinding
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.ViewState
import com.dariel25.android.pokeapp.presentation.pokelist.adapter.PokeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_pokelist.*

@AndroidEntryPoint
class PokeListActivity : BaseActivity() {

    private val pokeListViewModel by viewModels<PokeListViewModel>()
    private var pokeListAdapter: PokeListAdapter = PokeListAdapter(this)
    private val binding: ActivityPokelistBinding by lazy {
        ActivityPokelistBinding.inflate(layoutInflater)
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
    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokeListViewModel.fetchPokemons()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
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

    private fun updateViewStatus(networkState: ViewState<List<PokemonSimple>?>) {
        when (networkState) {
            is ViewState.Loading -> showLoadingView()
            is ViewState.Success -> loadList(networkState.data)
            is ViewState.Error -> showErrorView(networkState.message)
        }
    }

    private fun loadList(list: List<PokemonSimple>?) {
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

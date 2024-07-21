package com.dariel94.android.pokeapp.presentation.pokelist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.databinding.PokeappActivityPokelistBinding
import com.dariel94.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel94.android.pokeapp.presentation.model.PokeListData
import com.dariel94.android.pokeapp.presentation.model.UIState
import com.dariel94.android.pokeapp.presentation.pokelist.adapter.PokeListAdapter
import com.dariel94.android.pokeapp.presentation.utils.hide
import com.dariel94.android.pokeapp.presentation.utils.show
import com.dariel94.android.pokeapp.presentation.widgets.filter.FilterBottomSheet
import com.dariel94.android.pokeapp.presentation.widgets.filter.OptionFilterListener
import dagger.hilt.android.AndroidEntryPoint
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.dariel94.android.pokeapp.presentation.detail.PokemonDetailActivity
import com.dariel94.android.pokeapp.presentation.detail.adapter.PokeListListener
import com.dariel94.android.pokeapp.presentation.utils.LanguageUtils
import com.dariel94.android.pokeapp.presentation.utils.StringUtils


/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokeListActivity : BaseActivity(), OptionFilterListener, PokeListListener {

    private val pokeListViewModel by viewModels<PokeListViewModel>()
    private var pokeListAdapter: PokeListAdapter = PokeListAdapter(this, this)
    private lateinit var searchView: SearchView
    private val filterBottomSheet: FilterBottomSheet by lazy {
        FilterBottomSheet(this)
    }
    private val binding: PokeappActivityPokelistBinding by lazy {
        PokeappActivityPokelistBinding.inflate(layoutInflater)
    }

    private val pokeListActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ::onActionDefaultLauncher
    )

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
        searchView.queryHint = getString(R.string.pokeapp_search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                pokeListAdapter.filter.filter(newText)
                binding.emptyStateView.hide()
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

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language_es -> {
                LanguageUtils.setLocale("es", this)
                true
            }
            R.id.action_language_en -> {
                LanguageUtils.setLocale("en", this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        pokemonListData.types?.let {
            filterBottomSheet.setFilterData(
            pokemonListData.types ?: emptyList(),
            pokemonListData.generations ?: emptyList(),
            pokemonListData.categories ?: emptyList())
        }
        pokeListAdapter.setFavoritesList(pokemonListData.favourites)
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
        binding.emptyStateView.hide()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onActionDefaultLauncher(result: ActivityResult) {
        pokeListViewModel.fetchFavorites()
    }

    override fun onPokemonClicked(id: String) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(StringUtils.ID_PARAM, id)
        pokeListActivityResultLauncher.launch(intent)
    }

    override fun onEmptyList() {
        binding.emptyStateView.show()
    }
}

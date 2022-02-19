package com.dariel25.android.pokeapp.presentation.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.databinding.PokeappActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.detail.adapter.PokemonPagerAdapter
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.utils.*
import com.dariel25.android.pokeapp.presentation.widgets.PokemonTypeWidget
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokemonDetailActivity : BaseActivity() {

    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()
    private val binding: PokeappActivityPokemonDetailBinding by lazy {
        PokeappActivityPokemonDetailBinding.inflate(layoutInflater)
    }
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        supportActionBar?.hide()
        binding.toolbar.setNavigationOnClickListener { finish() }
        setUpCollapsingToolBar()

        pokemonDetailViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        id = intent.getStringExtra("id").toString()
        pokemonDetailViewModel.fetchPokemon(id)
    }

    override fun getLayoutView() : View = binding.root

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRetry() {
        pokemonDetailViewModel.fetchPokemon(id)
    }

    private fun updateViewStatus(networkState: UIState<PokemonUI?>) {
        when (networkState) {
            is UIState.Loading -> showLoadingView()
            is UIState.Success -> showPokemonData(networkState.data)
            is UIState.Error -> showErrorView(networkState.message)
        }
    }

    private fun showPokemonData(pokemon: PokemonUI?) {
        pokemon?.let {
            UIUtils.changeStatusBarColor(this, pokemon.color)
            setPokemonColor(pokemon.color)

            Glide.with(this)
                .load(pokemon.imageUrl)
                .centerCrop()
                .placeholder(UIUtils.getLoadingPlaceholder(this) as Drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.collapsingToolbarLayout.title = it.name.firstCharUpperCase()
            binding.id.text = PokemonUtils.getIdTitle(it.id)
            binding.desc.text = pokemon.desc

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            if (pokemon.isLegendary) {
                binding.legendaryTextView.show()
            }

            setUpTabView(pokemon)

            showLayoutView()
        }
    }

    private fun setUpTabView(pokemon: PokemonUI) {
        val pagerAdapter = PokemonPagerAdapter(this, pokemon)
        binding.pager.adapter = pagerAdapter
        val tableLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, pos ->
            when (pos) {
                0 -> tab.text = "     Stats     "
                1 -> tab.text = " Evolutions "
                2 -> tab.text = "    Abilities    "
            }
        }
        tableLayoutMediator.attach()
    }

    private fun setPokemonColor(color: Int) {
        val colorInt = ContextCompat.getColor(applicationContext, color)
        binding.tabLayout.setTabTextColors(colorInt, Color.WHITE)
        binding.tabLayout.setSelectedTabIndicatorColor(colorInt)
        binding.pokeappAppbarlayout.background = ContextCompat.getDrawable(this, color)
        binding.bottomSheetBackground.background = ContextCompat.getDrawable(this, color)
        binding.collapsingToolbarLayout.contentScrim = ContextCompat.getDrawable(this, color)
    }

    private fun setUpCollapsingToolBar() {
        binding.pokeappAppbarlayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                val h = binding.collapsingToolbarLayout.height
                val alpha = (verticalOffset * 3 + h).toFloat() / h
                binding.image.alpha = alpha
                binding.typesContainer.alpha = alpha
                binding.rightContainer.alpha = alpha
                if (verticalOffset == h) {
                    binding.bottomSheetBackground.show()
                } else {
                    binding.bottomSheetBackground.hide()
                }
            })
    }
}

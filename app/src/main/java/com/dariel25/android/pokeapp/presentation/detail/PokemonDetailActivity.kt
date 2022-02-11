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
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.databinding.PokeappActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.detail.adapter.PokemonPagerAdapter
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.utils.UIUtils
import com.dariel25.android.pokeapp.presentation.widgets.PokemonTypeWidget
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.pokeapp_activity_pokemon_detail.*

/**
 * Created by dariel94 on 31/10/2021.
 */
@AndroidEntryPoint
class PokemonDetailActivity : BaseActivity() {

    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()
    private val binding: PokeappActivityPokemonDetailBinding by lazy {
        PokeappActivityPokemonDetailBinding.inflate(layoutInflater)
    }
    private var pagerAdapter: PokemonPagerAdapter? = null
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            Glide.with(this)
                .load(pokemon.imageUrl)
                .centerCrop()
                .placeholder(UIUtils.getLoadingPlaceholder(this) as Drawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.id.text = PokemonUtils.getIdTitle(it.id)
            binding.statHeight.text = pokemon.height
            binding.statWeight.text = pokemon.weight

            binding.detailContainer.background = ContextCompat.getDrawable(this, pokemon.color)
            setUpActionbar(pokemon.color)

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            pagerAdapter = PokemonPagerAdapter(this, pokemon)
            binding.pager.adapter = pagerAdapter

            binding.tabLayout.setTabTextColors(
                ContextCompat.getColor(applicationContext, pokemon.color), Color.WHITE)
            binding.tabLayout.setSelectedTabIndicatorColor(
                ContextCompat.getColor(applicationContext, pokemon.color))

            val tableLayoutMediator = TabLayoutMediator(binding.tabLayout, pager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = "     Stats     "
                    }
                    1 -> {
                        tab.text = " Evolutions "
                    }
                    2 -> {
                        tab.text = "     Moves     "
                    }
                }
            }
            tableLayoutMediator.attach()
        }
        showLayoutView()
    }

    private fun setUpActionbar(color: Int) {
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.pokeapp_ic_arrow_white)
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(ContextCompat.getDrawable(applicationContext, color))
            elevation = 0f
            title = ""
        }
        UIUtils.changeStatusBarColor(this, color)
    }
}

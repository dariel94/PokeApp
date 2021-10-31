package com.dariel25.android.pokeapp.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.databinding.PokeappActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.PokemonUI
import com.dariel25.android.pokeapp.presentation.model.UIState
import com.dariel25.android.pokeapp.presentation.utils.UIUtils
import com.dariel25.android.pokeapp.presentation.widgets.PokemonTypeWidget
import com.dariel25.android.pokeapp.presentation.widgets.StatWidget
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

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.pokeapp_ic_arrow_white)
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
            title = ""
        }

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
                .placeholder(UIUtils.getLoadingPlaceholder(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.id.text = PokemonUtils.getIdTitle(it.id)
            binding.statHeight.text = pokemon.height
            binding.statWeight.text = pokemon.weight

            binding.detailContainer.background = ContextCompat.getDrawable(this, pokemon.color)
            supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, pokemon.color))

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            for (stat in pokemon.stats) {
                val statWidget = StatWidget(this)
                statWidget.setStat(stat, pokemon.color)
                binding.statsContainer.addView(statWidget)
            }
        }
        showLayoutView()
    }
}

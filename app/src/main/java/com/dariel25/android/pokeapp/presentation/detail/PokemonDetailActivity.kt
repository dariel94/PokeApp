package com.dariel25.android.pokeapp.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.databinding.ActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.ViewState
import com.dariel25.android.pokeapp.presentation.utils.StringUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailActivity : BaseActivity() {

    private val pokemonDetailViewModel by viewModels<PokemonDetailViewModel>()
    private val binding: ActivityPokemonDetailBinding by lazy {
        ActivityPokemonDetailBinding.inflate(layoutInflater)
    }
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonDetailViewModel.getViewStateLiveData()
            .observe(this, { updateViewStatus(it) })

        id = intent.getStringExtra("id").toString()
        pokemonDetailViewModel.fetchPokemon(id)
    }

    override fun getLayoutView() : View = binding.root

    override fun onRetry() {
        pokemonDetailViewModel.fetchPokemon(id)
    }

    private fun updateViewStatus(networkState: ViewState<Pokemon?>) {
        when (networkState) {
            is ViewState.Loading -> showLoadingView()
            is ViewState.Success -> showPokemonData(networkState.data)
            is ViewState.Error -> showErrorView(networkState.message)
        }
    }

    private fun showPokemonData(pokemon: Pokemon?) {
        pokemon?.let {
            Glide.with(this)
                .load(StringUtils.SPRITE_URL.replace("{id}", pokemon.id))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.height.text = it.height.toString()
            binding.weight.text = it.weight.toString()

            var types = ""
            for (type in pokemon.types) {
                types += "$type "
            }
            val textViewTypes = TextView(this)
            textViewTypes.text = types
            binding.typesContainer.addView(textViewTypes)

            for (stat in pokemon.stats) {
                val textViewStat = TextView(this)
                val textStat = stat.name + ": " + stat.value
                textViewStat.text = textStat
                binding.statsContainer.addView(textViewStat)
            }
        }
        showLayoutView()
    }
}

package com.dariel25.android.pokeapp.presentation.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.databinding.ActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.ViewState
import com.dariel25.android.pokeapp.presentation.utils.ColorUtils
import com.dariel25.android.pokeapp.presentation.utils.StringUtils
import com.dariel25.android.pokeapp.presentation.widgets.PokemonTypeWidget
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
        //supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

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
                .load(StringUtils.getImageUrl(id))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)

            binding.name.text = it.name.replaceFirstChar { c -> c.uppercase() }
            binding.id.text = StringUtils.getIdTitle(it.id)

            if (pokemon.types.isNotEmpty()) {
                val type = pokemon.types.first()
                binding.detailContainer.background =
                    ColorDrawable(ColorUtils.getPokemonTypeColor(this, type))
            }

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(type)
                binding.typesContainer.addView(pokemonTypeWidget)
            }

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

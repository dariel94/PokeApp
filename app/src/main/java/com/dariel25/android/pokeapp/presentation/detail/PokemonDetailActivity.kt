package com.dariel25.android.pokeapp.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dariel25.android.pokeapp.databinding.ActivityPokemonDetailBinding
import com.dariel25.android.pokeapp.domain.model.Pokemon
import com.dariel25.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel25.android.pokeapp.presentation.model.ViewState
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
            binding.id.text = it.id
            binding.name.text = it.name
            binding.height.text = it.height.toString()
            binding.weight.text = it.weight.toString()
        }
        showLayoutView()
    }
}

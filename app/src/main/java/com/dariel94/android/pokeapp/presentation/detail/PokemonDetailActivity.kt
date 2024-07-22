package com.dariel94.android.pokeapp.presentation.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.databinding.PokeappActivityPokemonDetailBinding
import com.dariel94.android.pokeapp.domain.model.Pokemon
import com.dariel94.android.pokeapp.presentation.core.ui.BaseActivity
import com.dariel94.android.pokeapp.presentation.detail.adapter.PokemonPagerAdapter
import com.dariel94.android.pokeapp.presentation.mapper.toUI
import com.dariel94.android.pokeapp.presentation.model.PokemonUI
import com.dariel94.android.pokeapp.presentation.model.UIState
import com.dariel94.android.pokeapp.presentation.utils.LanguageUtils
import com.dariel94.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel94.android.pokeapp.presentation.utils.StringUtils
import com.dariel94.android.pokeapp.presentation.utils.UIUtils
import com.dariel94.android.pokeapp.presentation.utils.show
import com.dariel94.android.pokeapp.presentation.widgets.PokemonTypeWidget
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

    private val id: String by lazy {
        intent.getStringExtra(StringUtils.ID_PARAM).toString()
    }

    private var favItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        supportActionBar?.hide()
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        setUpCollapsingToolBar()

        pokemonDetailViewModel.getViewStateLiveData()
            .observe(this) { updateViewStatus(it) }
    }

    override fun getLayoutView() : View = binding.root

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.pokeapp_detail_menu, menu)
        favItem = menu.findItem(R.id.action_favorite)

        pokemonDetailViewModel.fetchPokemon(id, LanguageUtils.getLanguage(this))

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                item.icon = if (item.isChecked) {
                    item.isChecked = false
                    getDrawable(this, R.drawable.pokeapp_ic_favorite_border)
                } else {
                    item.isChecked = true
                    getDrawable(this, R.drawable.pokeapp_ic_favorite)
                }
                pokemonDetailViewModel.setFavorite(item.isChecked)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRetry() {
        pokemonDetailViewModel.fetchPokemon(id, LanguageUtils.getLanguage(this))
    }

    private fun updateViewStatus(networkState: UIState<Pokemon?>) {
        when (networkState) {
            is UIState.Loading -> showLoadingView()
            is UIState.Success -> showPokemonData(networkState.data?.toUI(this))
            is UIState.Error -> showErrorView(getString(R.string.pokeapp_detail_error_message))
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

            binding.collapsingToolbarLayout.title = it.name
            binding.id.text = it.id
            binding.specie.text = it.specie
            binding.desc.text = pokemon.desc

            for (type in pokemon.types) {
                val pokemonTypeWidget = PokemonTypeWidget(this)
                pokemonTypeWidget.setType(PokemonUtils.getTypeTranslation(type, LanguageUtils.getLanguage(this)))
                binding.typesContainer.addView(pokemonTypeWidget)
            }

            if (pokemon.isLegendary) {
                binding.legendaryTag.show()
            }
            if (pokemon.isBaby) {
                binding.babyTag.show()
            }
            if (pokemon.isMythical) {
                binding.mythicTag.show()
            }

            favItem?.apply {
                isChecked = pokemon.isFavorite
                if (pokemon.isFavorite) {
                    isChecked = true
                    icon = getDrawable(applicationContext, R.drawable.pokeapp_ic_favorite)
                }
            }

            setUpTabView(pokemon)

            showLayoutView()
        }
    }

    private fun setUpTabView(pokemon: PokemonUI) {
        val pagerAdapter = PokemonPagerAdapter(this, pokemon)
        binding.pager.adapter = pagerAdapter
        val tableLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab, pos ->
            tab.text = pagerAdapter.tabTitles[pos]
        }
        tableLayoutMediator.attach()
    }

    private fun setPokemonColor(color: Int) {
        val colorInt = getColor(applicationContext, color)
        binding.tabLayout.setTabTextColors(colorInt, Color.WHITE)
        binding.tabLayout.setSelectedTabIndicatorColor(colorInt)
        binding.pokeappAppbarlayout.background = getDrawable(this, color)
        binding.collapsingToolbarLayout.contentScrim = getDrawable(this, color)
    }

    private fun setUpCollapsingToolBar() {
        binding.pokeappAppbarlayout.addOnOffsetChangedListener { _, verticalOffset ->
            val h = binding.collapsingToolbarLayout.height
            val alpha = (verticalOffset * 3 + h).toFloat() / h
            binding.image.alpha = alpha
            binding.typesContainer.alpha = alpha
            binding.rightContainer.alpha = alpha
        }
    }
}

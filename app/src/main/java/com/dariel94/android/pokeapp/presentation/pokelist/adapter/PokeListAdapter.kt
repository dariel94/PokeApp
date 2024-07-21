package com.dariel94.android.pokeapp.presentation.pokelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel94.android.pokeapp.presentation.detail.adapter.PokeListListener
import com.dariel94.android.pokeapp.presentation.model.PokemonSimpleUI
import com.dariel94.android.pokeapp.presentation.utils.LanguageUtils
import com.dariel94.android.pokeapp.presentation.utils.UIUtils

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokeListAdapter(
    val context: Context,
    private val pokeListListener: PokeListListener,
) : RecyclerView.Adapter<PokeListAdapter.PokemonViewHolder>(), Filterable {

    private val filter = PokemonListFilter(this)
    var dataset: List<PokemonSimpleUI> = ArrayList()
        set(value) {
            field = value
            filteredDataset = field
            filter.list = field
        }
    var filteredDataset = dataset

    fun setFavoritesList(list: List<String>?) {
        filter.favoritesList = list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): PokemonViewHolder =
        PokemonViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokeapp_pokemon_row, viewGroup, false))

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, pos: Int) {
        val pokemon = filteredDataset[pos]
        val lan = LanguageUtils.getLanguage(context)
        viewHolder.id.text = PokemonUtils.getIdTitle(pokemon.id)
        viewHolder.name.text = pokemon.name
        viewHolder.type1.text = PokemonUtils.getTypeTranslation(pokemon.type1, lan)
        viewHolder.type2.text = PokemonUtils.getTypeTranslation(pokemon.type2, lan)
        viewHolder.card.setCardBackgroundColor(ContextCompat.getColor(context, pokemon.cardColor))

        if (pokemon.type2.isEmpty()) {
            viewHolder.type2Container.visibility = View.GONE
        } else {
            viewHolder.type2Container.visibility = View.VISIBLE
        }

        Glide.with(context)
            .load(pokemon.imageUrl)
            .centerCrop()
            .placeholder(UIUtils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.icon)

        viewHolder.itemView.setOnClickListener {
            pokeListListener?.onPokemonClicked(pokemon.id)
        }
    }

    override fun getItemCount(): Int = filteredDataset.size

    override fun getFilter(): PokemonListFilter = filter

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById<View>(R.id.card) as CardView
        val icon: ImageView = itemView.findViewById<View>(R.id.iv_icon) as ImageView
        val name: TextView = itemView.findViewById<View>(R.id.tv_name) as TextView
        val id: TextView = itemView.findViewById<View>(R.id.tv_id) as TextView
        val type1: TextView = itemView.findViewById<View>(R.id.tv_type1) as TextView
        val type2: TextView = itemView.findViewById<View>(R.id.tv_type2) as TextView
        val type2Container: LinearLayout = itemView.findViewById<View>(R.id.type2_container) as LinearLayout
    }
}

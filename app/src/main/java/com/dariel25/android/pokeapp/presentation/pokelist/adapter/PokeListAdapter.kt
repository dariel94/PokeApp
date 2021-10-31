package com.dariel25.android.pokeapp.presentation.pokelist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.detail.PokemonDetailActivity
import com.dariel25.android.pokeapp.presentation.model.PokemonSimpleUI
import com.dariel25.android.pokeapp.presentation.utils.UIUtils

/**
 * Created by dariel94 on 31/10/2021.
 */
class PokeListAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokeListAdapter.PokemonViewHolder>(), Filterable {

    private val filter = PokemonListFilter(this)
    var dataset: List<PokemonSimpleUI> = ArrayList()
        set(value) {
            field = value
            filteredDataset = field
        }
    var filteredDataset = dataset

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): PokemonViewHolder =
        PokemonViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokeapp_pokemon_row, viewGroup, false))

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, pos: Int) {
        val pokemon = filteredDataset[pos]

        viewHolder.id.text = PokemonUtils.getIdTitle(pokemon.id)
        viewHolder.name.text = pokemon.name
        viewHolder.type1.text = pokemon.type1
        viewHolder.type2.text = pokemon.type2
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
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra("id", pokemon.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredDataset.size

    override fun getFilter(): Filter = filter

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

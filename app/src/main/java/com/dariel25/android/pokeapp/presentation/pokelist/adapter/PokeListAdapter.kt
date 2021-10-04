package com.dariel25.android.pokeapp.presentation.pokelist.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.domain.model.PokemonSimple
import com.dariel25.android.pokeapp.presentation.utils.StringUtils
import java.util.*
import kotlin.collections.ArrayList


class PokeListAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokeListAdapter.PokemonViewHolder>(), Filterable {

    private val filter = PokemonListFilter(this)
    var dataset: List<PokemonSimple> = ArrayList()
        set(value) {
            field = value
            filteredDataset = field
        }
    var filteredDataset = dataset

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): PokemonViewHolder =
        PokemonViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_pokemon, viewGroup, false))

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, pos: Int) {
        val p = filteredDataset[pos]

        viewHolder.id.text = StringUtils.getIdTitle(p.id)
        viewHolder.name.text = p.name
        viewHolder.type1.text = p.type1
        viewHolder.type2.text = p.type2
        viewHolder.card.setCardBackgroundColor(getColor(p.type1))

        if (p.type2.isEmpty()) {
            viewHolder.type2Container.visibility = View.GONE
        } else {
            viewHolder.type2Container.visibility = View.VISIBLE
        }

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 3f
        circularProgressDrawable.centerRadius = 20f
        circularProgressDrawable.setColorSchemeColors(Color.WHITE)
        circularProgressDrawable.start()

        Glide.with(context)
            .load(IMAGE_URL + p.id + PNG)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.icon)
    }

    private fun getColor(type: String): Int {
        val c = when (type.lowercase(Locale.getDefault())) {
            "grass" -> R.color.grass
            "fire" -> R.color.fire
            "water" -> R.color.water
            "bug" -> R.color.bug
            "poison" -> R.color.poison
            "electric" -> R.color.electric
            "ground" -> R.color.ground
            "fairy" -> R.color.fairy
            "fighting" -> R.color.fighting
            "psychic" -> R.color.psychic
            "rock" -> R.color.rock
            "ghost" -> R.color.ghost
            "flying" -> R.color.flying
            "ice" -> R.color.ice
            "dragon" -> R.color.dragon
            "dark" -> R.color.dark
            else -> R.color.other
        }
        return context.resources.getColor(c)
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

    companion object {
        private const val IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
        private const val PNG = ".png"
    }
}

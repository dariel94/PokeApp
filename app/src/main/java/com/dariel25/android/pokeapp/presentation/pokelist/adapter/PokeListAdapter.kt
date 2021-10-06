package com.dariel25.android.pokeapp.presentation.pokelist.adapter

import android.content.Context
import android.content.Intent
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
import com.dariel25.android.pokeapp.domain.model.SimplePokemon
import com.dariel25.android.pokeapp.presentation.detail.PokemonDetailActivity
import com.dariel25.android.pokeapp.presentation.utils.ColorUtils
import com.dariel25.android.pokeapp.presentation.utils.StringUtils
import kotlin.collections.ArrayList

class PokeListAdapter(
    private val context: Context
) : RecyclerView.Adapter<PokeListAdapter.PokemonViewHolder>(), Filterable {

    private val filter = PokemonListFilter(this)
    var dataset: List<SimplePokemon> = ArrayList()
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
        viewHolder.card.setCardBackgroundColor(ColorUtils.getPokemonTypeColor(context, p.type1))

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
            .load(StringUtils.getImageUrl(p.id))
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.icon)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra("id", p.id)
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

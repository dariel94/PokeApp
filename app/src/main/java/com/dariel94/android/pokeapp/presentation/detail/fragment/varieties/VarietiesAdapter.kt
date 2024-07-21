package com.dariel94.android.pokeapp.presentation.detail.fragment.varieties

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.domain.model.Variety
import com.dariel94.android.pokeapp.presentation.detail.PokemonDetailActivity
import com.dariel94.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel94.android.pokeapp.presentation.utils.StringUtils
import com.dariel94.android.pokeapp.presentation.utils.UIUtils

/**
 * Created by dariel94 on 31/10/2021.
 */
class VarietiesAdapter(
    private val context: Context,
    private val dataset: List<Variety>
) : RecyclerView.Adapter<VarietiesAdapter.VarietyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): VarietyViewHolder =
        VarietyViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokeapp_variety_row, viewGroup, false))

    override fun onBindViewHolder(viewHolder: VarietyViewHolder, pos: Int) {
        val variety = dataset[pos]

        if (pos == 0) {
            viewHolder.itemView.setPadding(0, 0, 0, 0)
        }

        viewHolder.varietyNameTextView.text = variety.name

        Glide.with(context)
            .load(PokemonUtils.getImageUrl(variety.id))
            .centerCrop()
            .placeholder(UIUtils.getLoadingPlaceholder(context, Color.GRAY))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.varietyImageView)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra(StringUtils.ID_PARAM, variety.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = dataset.size

    class VarietyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val varietyImageView: ImageView = itemView.findViewById<View>(R.id.varietyImage) as ImageView
        val varietyNameTextView: TextView = itemView.findViewById<View>(R.id.varietyTextView) as TextView
    }
}

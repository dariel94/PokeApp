package com.dariel25.android.pokeapp.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.presentation.utils.PokemonUtils
import com.dariel25.android.pokeapp.presentation.utils.UIUtils

/**
 * Created by David on 5/10/2021.
 */
class EvolutionWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val evolutionFromImage: ImageView
    private val evolutionFromText: TextView
    private val evolutionToImage: ImageView
    private val evolutionToText: TextView
    private val minLevelText: TextView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.pokeapp_evolution_widget_layout, this)
        evolutionFromImage = findViewById(R.id.evolutionFromImage)
        evolutionFromText = findViewById(R.id.evolutionFromTextView)
        evolutionToImage = findViewById(R.id.evolutionToImage)
        evolutionToText = findViewById(R.id.evolutionToTextView)
        minLevelText = findViewById(R.id.evolutionLvl)
    }

    fun setData(fromId: String, fromName: String, toId: String, toName: String, minLevel: Int) {

        Glide.with(context)
            .load(PokemonUtils.getImageUrl(fromId))
            .centerCrop()
            .placeholder(UIUtils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(evolutionFromImage)

        evolutionFromText.text = fromName.replaceFirstChar(Char::uppercase)

        Glide.with(context)
            .load(PokemonUtils.getImageUrl(toId))
            .centerCrop()
            .placeholder(UIUtils.getLoadingPlaceholder(context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(evolutionToImage)

        evolutionToText.text = toName.replaceFirstChar(Char::uppercase)

        val minLvlText = "Lvl $minLevel"
        minLevelText.text = minLvlText
    }
}

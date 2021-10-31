package com.dariel25.android.pokeapp.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.dariel25.android.pokeapp.R

/**
 * Created by David on 5/10/2021.
 */
class PokemonTypeWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val typeTextView: TextView
    private val typeContainer: LinearLayout

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.pokeapp_type_widget_layout, this)
        typeTextView = findViewById(R.id.type_widget_name)
        typeContainer = findViewById(R.id.type_widget_container)
    }

    fun setType(type: String) {
        typeTextView.text = type.replaceFirstChar { c -> c.uppercase() }
    }
}
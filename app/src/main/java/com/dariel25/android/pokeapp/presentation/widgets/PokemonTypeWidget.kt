package com.dariel25.android.pokeapp.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.dariel25.android.pokeapp.R

/**
 * Created by David on 5/10/2021.
 */
class PokemonTypeWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val typeTextView: TextView

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.pokeapp_type_widget_layout, this)
        typeTextView = findViewById(R.id.type_widget_name)
    }

    fun setType(type: String) {
        typeTextView.text = type
    }
}
package com.dariel25.android.pokeapp.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dariel25.android.pokeapp.R

/**
 * Created by dariel94 on 9/1/2022.
 */
class MovesFragment(
    private val moves: List<String>
): Fragment() {

    private lateinit var movesContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_base, container, false)
        movesContainer = view.findViewById(R.id.container)
        for (move in moves) {
            val tv = TextView(view.context)
            tv.text = move
            movesContainer.addView(tv)
        }
        return view
    }
}
package com.dariel25.android.pokeapp.presentation.detail.fragment.moves

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dariel25.android.pokeapp.R

/**
 * Created by dariel94 on 31/10/2021.
 */
class MovesAdapter(
    private val context: Context,
    private val dataset: List<String>
) : RecyclerView.Adapter<MovesAdapter.MovesViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): MovesViewHolder =
        MovesViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokeapp_move_row, viewGroup, false))

    override fun onBindViewHolder(viewHolder: MovesViewHolder, pos: Int) {
        viewHolder.moveTextView.text = dataset[pos]
    }

    override fun getItemCount(): Int = dataset.size

    class MovesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moveTextView: TextView = itemView.findViewById<View>(R.id.moveTextView) as TextView
    }
}

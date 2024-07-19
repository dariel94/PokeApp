package com.dariel94.android.pokeapp.presentation.detail.fragment.abilities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.domain.model.Ability
import com.dariel94.android.pokeapp.presentation.utils.normalizeProperty

/**
 * Created by dariel94 on 31/10/2021.
 */
class AbilityAdapter(
    private val context: Context,
    private val dataset: List<Ability>
) : RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): AbilityViewHolder =
        AbilityViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokeapp_ability_row, viewGroup, false))

    override fun onBindViewHolder(viewHolder: AbilityViewHolder, pos: Int) {
        val name = dataset[pos].name.normalizeProperty()
        viewHolder.abilityTextView.text = name

        viewHolder.abilityInfoIcon.setOnClickListener {
            val alertDialog: AlertDialog =
                AlertDialog.Builder(context, R.style.pokeapp_abilityDialog).create()
            alertDialog.setTitle(name)
            alertDialog.setMessage(dataset[pos].flavorText)
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int = dataset.size

    class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abilityTextView: TextView = itemView.findViewById<View>(R.id.abilityTextView) as TextView
        val abilityInfoIcon: View = itemView.findViewById(R.id.abilityInfo) as View
    }
}

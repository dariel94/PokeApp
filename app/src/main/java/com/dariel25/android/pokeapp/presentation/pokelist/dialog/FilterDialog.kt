/*
 * Created by dariel94 on 8/3/22 06:38
 * Last modified 8/3/22 06:38
 */

package com.dariel25.android.pokeapp.presentation.pokelist.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.presentation.model.OptionFilters
import com.dariel25.android.pokeapp.presentation.utils.capitalizeFirst
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterDialog: DialogFragment() {

    var listener: OptionFilterListener? = null
    private lateinit var typesGroup: ChipGroup
    private lateinit var gensGroup: ChipGroup
    var optionFilters: OptionFilters? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_filter_dialog, container, false)

        typesGroup = view.findViewById(R.id.types_group)
        gensGroup = view.findViewById(R.id.gen_group)

        view.findViewById<Button>(R.id.filter_button).setOnClickListener {
            val types = typesGroup.checkedChipIds.map {
                typesGroup.findViewById<Chip>(it).text.toString()
            }
            val gens = gensGroup.checkedChipIds.map {
                gensGroup.findViewById<Chip>(it).text.toString()
            }
            listener?.setFilterData(OptionFilters(types, gens))
            dismiss()
        }

        setFilterData()

        return view
    }

    private fun setFilterData() {
        optionFilters?.let {
            for (type in it.types) {
                val chip = layoutInflater
                    .inflate(R.layout.pokeapp_filter_chip, typesGroup, false) as Chip
                chip.text = type.capitalizeFirst()
                typesGroup.addView(chip)
            }
            for (generationId in it.generations.indices) {
                val chip = layoutInflater
                    .inflate(R.layout.pokeapp_filter_chip, gensGroup, false) as Chip
                val genString = (generationId + 1).toString()
                chip.text = genString
                gensGroup.addView(chip)
            }
        }
    }
}

/*
 * Created by dariel94 on 30/5/22 22:58
 * Last modified 30/5/22 22:50
 */

package com.dariel25.android.pokeapp.presentation.widgets.filter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.presentation.model.FilterData
import com.dariel25.android.pokeapp.presentation.model.FilterOption
import com.dariel25.android.pokeapp.presentation.utils.capitalizeFirst
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * Created by David on 5/10/2021.
 */
class FilterBottomSheet(context: Context) : BottomSheetDialog(context) {

    var listener: OptionFilterListener? = null
    private var typesGroup: ChipGroup
    private var gensGroup: ChipGroup
    private var catsGroup: ChipGroup
    private var options: FilterData = FilterData(emptyList(), emptyList(), emptyList())

    init {
        val view = layoutInflater
            .inflate(R.layout.pokeapp_pokemon_filter_view_layout, null)
        setContentView(view)

        typesGroup = view.findViewById(R.id.types_group)
        gensGroup = view.findViewById(R.id.gen_group)
        catsGroup = view.findViewById(R.id.cat_group)

        view.findViewById<Button>(R.id.filter_button).setOnClickListener {
            val selectedTypes = typesGroup.checkedChipIds.map {
                typesGroup.findViewById<Chip>(it).text.toString()
            }
            val selectedGens = gensGroup.checkedChipIds.map {
                gensGroup.findViewById<Chip>(it).text.toString()
            }
            val selectedCats = catsGroup.checkedChipIds.map {
                catsGroup.findViewById<Chip>(it).text.toString()
            }
            listener?.onFilterData(selectedTypes, selectedGens, selectedCats)
            dismiss()
        }

        setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    fun setFilterData(types: List<String>, gens: List<String>, cats: List<String>) {
        val typeOptions = types.map { FilterOption(it, false) }
        val genOptions = gens.map { FilterOption(it, false) }
        val catOptions = cats.map { FilterOption(it, false) }
        options = FilterData(typeOptions, genOptions, catOptions)

        loadFilterData()
    }

    @SuppressLint("SetTextI18n")
    private fun loadFilterData() {
        typesGroup.removeAllViews()
        gensGroup.removeAllViews()
        catsGroup.removeAllViews()
        options.types.forEach { type ->
            val chip = layoutInflater
                .inflate(R.layout.pokeapp_filter_chip, typesGroup, false) as Chip
            chip.text = type.name.capitalizeFirst()
            typesGroup.addView(chip)
        }
        options.generations.forEachIndexed { index, _ ->
            val chip = layoutInflater
                .inflate(R.layout.pokeapp_filter_chip, gensGroup, false) as Chip
            chip.text = (index + 1).toString()
            gensGroup.addView(chip)
        }
        options.categories.forEach { category ->
            val chip = layoutInflater
                .inflate(R.layout.pokeapp_filter_chip, catsGroup, false) as Chip
            chip.text = category.name.capitalizeFirst()
            catsGroup.addView(chip)
        }
    }

    fun clearSelections() {
        typesGroup.checkedChipIds.forEach {
            typesGroup.findViewById<Chip>(it).isChecked = false
        }
        gensGroup.checkedChipIds.forEach {
            gensGroup.findViewById<Chip>(it).isChecked = false
        }
        catsGroup.checkedChipIds.forEach {
            catsGroup.findViewById<Chip>(it).isChecked = false
        }
    }
}

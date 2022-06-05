package com.dariel25.android.pokeapp.presentation.detail.fragment.abilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.domain.model.Ability

/**
 * Created by dariel94 on 9/1/2022.
 */
class AbilitiesFragment(
    private val abilities: List<Ability>
): Fragment() {

    private lateinit var movesRecyclerView: RecyclerView
    private lateinit var adapter: AbilityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_moves, container, false)
        movesRecyclerView = view.findViewById(R.id.movesRecyclerView)
        context?.let { context ->
            adapter = AbilityAdapter(context, abilities)
            movesRecyclerView.adapter = adapter
            movesRecyclerView.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(context)
            movesRecyclerView.layoutManager = layoutManager
            adapter.notifyDataSetChanged()
        }
        return view
    }
}
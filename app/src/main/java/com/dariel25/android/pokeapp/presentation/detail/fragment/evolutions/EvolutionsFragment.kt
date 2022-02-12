package com.dariel25.android.pokeapp.presentation.detail.fragment.evolutions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.presentation.model.Evolution

/**
 * Created by dariel94 on 9/1/2022.
 */
class EvolutionsFragment(
    private val evolutions: List<Evolution>
): Fragment() {

    private lateinit var evolutionsRecyclerView: RecyclerView
    private lateinit var adapter: EvolutionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_evolution, container, false)
        evolutionsRecyclerView = view.findViewById(R.id.evolutionsRecyclerView)

        context?.let { context ->
            if (evolutions.isNullOrEmpty()) {
                evolutionsRecyclerView.visibility = View.GONE
                view.findViewById<View>(R.id.empty_state).visibility = View.VISIBLE
            } else {
                adapter = EvolutionsAdapter(context, evolutions)
                evolutionsRecyclerView.adapter = adapter
                evolutionsRecyclerView.setHasFixedSize(true)
                val layoutManager = LinearLayoutManager(context)
                evolutionsRecyclerView.layoutManager = layoutManager
                adapter.notifyDataSetChanged()
            }
        }
        return view
    }
}

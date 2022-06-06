package com.dariel25.android.pokeapp.presentation.detail.fragment.varieties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariel25.android.pokeapp.R
import com.dariel25.android.pokeapp.domain.model.Variety

/**
 * Created by dariel94 on 9/1/2022.
 */
class VarietiesFragment(
    private val varieties: List<Variety>
): Fragment() {

    private lateinit var varietiesRecyclerView: RecyclerView
    private lateinit var adapter: VarietiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pokeapp_detail_fragment_variety, container, false)
        varietiesRecyclerView = view.findViewById(R.id.varietiesRecyclerView)
        context?.let { context ->
            adapter = VarietiesAdapter(context, varieties)
            varietiesRecyclerView.adapter = adapter
            varietiesRecyclerView.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(context)
            varietiesRecyclerView.layoutManager = layoutManager
            adapter.notifyDataSetChanged()
        }
        return view
    }
}

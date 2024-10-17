package com.example.tinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MisLikesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MarcaAdapter
    //private val viewModel: MarcasViewModel by viewModels()
    private val viewModel: MarcasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MarcaAdapter()
        recyclerView.adapter = adapter

        viewModel.misLikes.observe(viewLifecycleOwner) { likes ->
            adapter.submitList(likes)
        }

        return view
    }
}

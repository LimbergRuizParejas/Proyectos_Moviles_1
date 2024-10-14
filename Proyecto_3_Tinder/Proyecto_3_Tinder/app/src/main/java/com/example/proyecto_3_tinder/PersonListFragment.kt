package com.example.proyecto_3_tinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2

class PersonListFragment : Fragment() {
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_person_list, container, false)

        val personNameTextView: TextView = view.findViewById(R.id.person_name_text_view)
        val photoViewPager: ViewPager2 = view.findViewById(R.id.photo_view_pager)
        val likeButton: Button = view.findViewById(R.id.like_button)
        val dislikeButton: Button = view.findViewById(R.id.dislike_button)

        viewModel.people.observe(viewLifecycleOwner, Observer { people ->
            if (people.isNotEmpty()) {
                val person = people[0]
                personNameTextView.text = person.name
                Log.d("PersonListFragment", "Cargando imágenes de: ${person.name}")

                val adapter = PhotoPagerAdapter(person.photos)
                photoViewPager.adapter = adapter

                likeButton.setOnClickListener {
                    viewModel.likePerson(person)
                }

                dislikeButton.setOnClickListener {
                    viewModel.removePerson(person)
                }
            } else {
                Log.d("PersonListFragment", "No hay más personas en la lista")
                personNameTextView.text = "No hay más personas"
                photoViewPager.adapter = null
            }
        })

        return view
    }
}

package com.example.proyecto_3_tinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class PersonAdapter(
    private var people: List<Person>,
    private val viewModel: PersonViewModel
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    fun setPeople(people: List<Person>) {
        this.people = people
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.nameTextView.text = person.name
        val adapter = PhotoPagerAdapter(person.photos)
        holder.viewPager.adapter = adapter
    }

    override fun getItemCount() = people.size

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val viewPager: ViewPager2 = itemView.findViewById(R.id.photo_view_pager)
    }
}

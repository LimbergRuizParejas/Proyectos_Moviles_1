package com.example.practico_4_lista_de_contactos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactosAdapter(private var contactos: List<Contacto>) :
    RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacto, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactos[position]
        holder.bind(contacto)
    }

    override fun getItemCount(): Int {
        return contactos.size
    }

    fun updateContactos(newContactos: List<Contacto>) {
        contactos = newContactos
        notifyDataSetChanged()
    }

    class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        private val companyTextView: TextView = itemView.findViewById(R.id.companyTextView)

        fun bind(contacto: Contacto) {
            nameTextView.text = contacto.name
            lastNameTextView.text = contacto.last_name
            companyTextView.text = contacto.company

            // Use Glide to load the profile picture
            Glide.with(itemView.context)
                .load(contacto.profile_picture)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(profileImageView)
        }
    }
}

// FotosPagerAdapter.kt
package com.example.tinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tinder.databinding.ItemPhotoBinding

class FotosPagerAdapter(fragment: Fragment) : RecyclerView.Adapter<FotosPagerAdapter.FotoViewHolder>() {
    private var fotos = listOf<String>()
    private var nombre: String = ""

    fun updateFotos(nuevasFotos: List<String>, nuevoNombre: String) {
        fotos = nuevasFotos
        nombre = nuevoNombre
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val foto = fotos[position]
        // Cargar la imagen con Glide
        Glide.with(holder.itemView.context)
            .load(foto) // Ruta de la imagen
            .into(holder.binding.imageView)
        holder.binding.textViewNombre.text = nombre
    }

    override fun getItemCount() = fotos.size

    inner class FotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)
}

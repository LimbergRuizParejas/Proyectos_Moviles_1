// MarcaAdapter.kt
package com.example.tinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.tinder.databinding.ItemBrandBinding

class MarcaAdapter : ListAdapter<Marca, MarcaAdapter.MarcaViewHolder>(MarcaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarcaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        val marca = getItem(position)
        holder.bind(marca)
    }

    inner class MarcaViewHolder(private val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marca: Marca) {
            binding.textViewNombre.text = marca.nombre
            // Cargar la primera foto con Glide aplicando circleCrop
            if (marca.fotos.isNotEmpty()) {
                Glide.with(binding.imageView.context)
                    .load(marca.fotos[0]) // Ruta de la imagen
                    .transform(CircleCrop()) // Aplicar circleCrop para redondear la imagen
                    .into(binding.imageView)
            }
        }
    }

    class MarcaDiffCallback : DiffUtil.ItemCallback<Marca>() {
        override fun areItemsTheSame(oldItem: Marca, newItem: Marca): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Marca, newItem: Marca): Boolean {
            return oldItem == newItem
        }
    }
}

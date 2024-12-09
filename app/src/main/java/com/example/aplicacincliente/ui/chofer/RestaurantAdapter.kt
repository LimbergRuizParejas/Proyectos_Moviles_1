package com.example.aplicacioncliente.ui.chofer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacincliente.api.Restaurante
import com.example.aplicacioncliente.R

class RestaurantAdapter(
    private val restaurants: List<Restaurante>,
    private val listener: (Restaurante) -> Unit
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.restaurantNameTextView.text = restaurant.nombre
        holder.restaurantAddressTextView.text = restaurant.direccion
        holder.itemView.setOnClickListener { listener(restaurant) }
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantNameTextView: TextView = itemView.findViewById(R.id.restaurantNameTextView)
        val restaurantAddressTextView: TextView = itemView.findViewById(R.id.restaurantAddressTextView)
    }
}

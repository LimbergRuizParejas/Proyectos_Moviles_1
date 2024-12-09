package com.example.aplicacioncliente.ui.chofer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacincliente.models.Producto
import com.example.aplicacioncliente.R

class ProductAdapter(
    private val productos: List<Producto>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val producto = productos[position]
        holder.productNameTextView.text = producto.nombre
        holder.productDescriptionTextView.text = producto.descripcion
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        val productDescriptionTextView: TextView = itemView.findViewById(R.id.productDescriptionTextView)
    }
}

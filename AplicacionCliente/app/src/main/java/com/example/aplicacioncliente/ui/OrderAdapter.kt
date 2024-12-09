package com.example.aplicacioncliente.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionchofer.model.Pedido
import com.example.aplicacioncliente.R
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter(private val orders: List<Pedido>, private val clickListener: (Pedido) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order, clickListener)
    }

    override fun getItemCount(): Int = orders.size

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Pedido, clickListener: (Pedido) -> Unit) {
            itemView.orderDescription.text = order.descripcion
            itemView.orderStatus.text = order.estado
            itemView.setOnClickListener { clickListener(order) }
        }
    }
}

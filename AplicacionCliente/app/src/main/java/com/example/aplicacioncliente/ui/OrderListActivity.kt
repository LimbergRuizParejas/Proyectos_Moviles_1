package com.example.aplicacioncliente.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionchofer.model.Pedido
import com.example.aplicacioncliente.R
import com.example.aplicacioncliente.model.Pedido

class OrderListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Aquí puedes obtener la lista de pedidos y establecer el adaptador
        val orders = listOf<Pedido>() // Aquí debes obtener los datos de los pedidos
        orderAdapter = OrderAdapter(orders) { pedido ->
            // Lógica al hacer clic en un pedido
        }
        recyclerView.adapter = orderAdapter
    }
}

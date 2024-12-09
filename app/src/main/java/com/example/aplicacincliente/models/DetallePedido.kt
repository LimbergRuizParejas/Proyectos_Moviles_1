package com.example.aplicacincliente.models

data class DetallePedido(
    val id: Int,
    val idProducto: Int,
    val idPedido: Int,
    val cantidad: Int,
    val precio: Double
)

package com.example.aplicacincliente.api

data class Pedido(
    val id: Int,
    val idUsuarioSolicitante: Int,
    val idRestaurante: Int,
    val totalAPagar: Double,
    val fechaHora: String,
    val idChoferAsignado: Int? = null,
    val direccionEntrega: String,
    val latitud: Double,
    val longitud: Double,
    val estado: Int // 0 = Solicitado, 1 = Aceptado por chofer, 2 = En camino, 3 = Pedido finalizado
)

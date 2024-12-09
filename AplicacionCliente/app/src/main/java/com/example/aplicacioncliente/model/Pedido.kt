package com.example.aplicacionchofer.model

data class Pedido(
    val id: Int,
    val usuarioId: Int,
    val descripcion: String,
    val fecha: String,
    val estado: String,
    val ubicacionRecogida: String,
    val ubicacionEntrega: String
)

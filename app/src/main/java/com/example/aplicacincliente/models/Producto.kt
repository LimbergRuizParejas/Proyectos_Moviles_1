package com.example.aplicacincliente.models

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String,
    val restauranteId: Int
)

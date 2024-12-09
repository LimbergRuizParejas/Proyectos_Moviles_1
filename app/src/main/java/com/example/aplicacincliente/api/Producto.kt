package com.example.aplicacincliente.api

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val idRestaurante: Int,
    val foto: String? = null
)

package com.example.aplicacincliente.api

data class Restaurante(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val idUsuarioPropietario: Int,
    val logo: String? = null
)

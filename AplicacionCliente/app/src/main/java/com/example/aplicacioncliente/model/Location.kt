package com.example.aplicacionchofer.model

data class Location(
    val id: Int,
    val pedidoId: Int,
    val latitud: Double,
    val longitud: Double,
    val fechaHora: String
)

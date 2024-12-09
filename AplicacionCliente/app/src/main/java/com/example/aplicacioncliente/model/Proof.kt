package com.example.aplicacionchofer.model

data class Proof(
    val id: Int,
    val pedidoId: Int,
    val urlImagen: String,
    val fechaHora: String
)

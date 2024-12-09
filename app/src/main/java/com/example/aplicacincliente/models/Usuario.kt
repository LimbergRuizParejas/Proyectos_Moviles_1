package com.example.aplicacincliente.models

data class Usuario(
    val id: Int,
    val email: String,
    val password: String,
    val tipoUsuario: Int
)

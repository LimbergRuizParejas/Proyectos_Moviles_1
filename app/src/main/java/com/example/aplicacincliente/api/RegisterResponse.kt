package com.example.aplicacincliente.api

data class RegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: Int
)

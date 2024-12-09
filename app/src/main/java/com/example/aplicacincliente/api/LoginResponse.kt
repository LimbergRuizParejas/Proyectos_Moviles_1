package com.example.aplicacincliente.api

data class LoginResponse(
    val token: String,
    val user: UserDetails
)

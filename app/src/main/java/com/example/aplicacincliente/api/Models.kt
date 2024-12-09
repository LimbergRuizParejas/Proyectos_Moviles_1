package com.example.aplicacioncliente.api

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val userId: String)
data class Restaurant(val id: String, val name: String, val address: String) data class MenuItem(val name: String, val description: String)
data class RegisterRequest(val email: String, val password: String, val name: String, val role: Int)
data class RegisterResponse(val userId: String)

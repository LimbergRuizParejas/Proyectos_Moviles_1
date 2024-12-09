package com.example.aplicacincliente.models

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val restaurant_id: Int,
    val image: String
)

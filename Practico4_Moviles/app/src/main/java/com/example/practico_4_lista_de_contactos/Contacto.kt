package com.example.practico_4_lista_de_contactos

data class Contacto(
    val id: Int,
    val name: String,
    val last_name: String,
    val company: String,
    val address: String,
    val city: String,
    val state: String,
    val telefonos: List<Telefono>,
    val correos: List<Correo>,
    val profile_picture: String?
)

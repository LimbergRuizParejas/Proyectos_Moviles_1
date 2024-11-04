package com.example.practico_4_lista_de_contactos

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {
    @GET("personas")
    suspend fun getContactos(): List<Contacto>

    @GET("search")
    suspend fun buscarContactos(@Query("q") query: String): List<Contacto>

    @POST("personas")
    suspend fun addContacto(@Body contacto: Contacto): Contacto

    @PUT("personas/{id}")
    suspend fun updateContacto(@Path("id") id: Int, @Body contacto: Contacto): Contacto

    @DELETE("personas/{id}")
    suspend fun deleteContacto(@Path("id") id: Int)

    @GET("personas/{id}")
    suspend fun getContactoById(@Path("id") id: Int): Contacto

    @GET("phones")
    suspend fun getListaTelefonos(): List<Telefono>

    @POST("phones")
    suspend fun addTelefono(@Body telefono: Telefono): Telefono

    @Multipart
    @POST("personas/{id}/profile-picture")
    suspend fun subirFoto(@Path("id") id: Int, @Part foto: MultipartBody.Part): ResponseBody

    @POST("emails")
    suspend fun addCorreo(@Body correo: Correo): Correo

    @GET("emails")
    suspend fun getListaEmails(): List<Correo>
}

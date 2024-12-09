package com.example.aplicacioncliente.api

import com.example.aplicacincliente.models.Producto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductoApi {
    @POST("products")
    suspend fun crearProducto(@Body producto: Producto, @Header("Authorization") token: String): Response<Producto>

    @PUT("products/{id}")
    suspend fun actualizarProducto(@Path("id") id: Int, @Body producto: Producto, @Header("Authorization") token: String): Response<Producto>

    @DELETE("products/{id}")
    suspend fun eliminarProducto(@Path("id") id: Int, @Header("Authorization") token: String): Response<Unit>

    @POST("products/{id}/picture")
    suspend fun subirFotoProducto(@Path("id") id: Int, @Body foto: RequestBody, @Header("Authorization") token: String): Response<Unit>

    @GET("products")
    suspend fun getProductos(@Header("Authorization") token: String): Response<List<Producto>>

    @GET("products/{id}")
    suspend fun getProducto(@Path("id") id: Int, @Header("Authorization") token: String): Response<Producto>
}

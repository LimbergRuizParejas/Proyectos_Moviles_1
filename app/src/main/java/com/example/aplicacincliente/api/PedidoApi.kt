package com.example.aplicacioncliente.api

import com.example.aplicacincliente.api.Pedido
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PedidoApi {
    @POST("pedidos")
    suspend fun crearPedido(@Body pedido: Pedido, @Header("Authorization") token: String): Response<Pedido>

    @GET("pedidos")
    suspend fun getPedidos(@Header("Authorization") token: String): Response<List<Pedido>>

    @GET("pedidos/sinasignar")
    suspend fun getPedidosSinAsignar(@Header("Authorization") token: String): Response<List<Pedido>>

    @GET("pedidos/{id}")
    suspend fun getPedido(@Path("id") id: Int, @Header("Authorization") token: String): Response<Pedido>

    @PUT("pedidos/{id}/aceptar")
    suspend fun aceptarPedido(@Path("id") id: Int, @Header("Authorization") token: String): Response<Pedido>

    @PUT("pedidos/{id}/encamino")
    suspend fun pedidoEnCamino(@Path("id") id: Int, @Header("Authorization") token: String): Response<Pedido>

    @PUT("pedidos/{id}/entregado")
    suspend fun pedidoEntregado(@Path("id") id: Int, @Header("Authorization") token: String): Response<Pedido>

    @POST("pedidos/{id}/foto")
    suspend fun pruebaEntrega(@Path("id") id: Int, @Body foto: RequestBody, @Header("Authorization") token: String): Response<Unit>
}

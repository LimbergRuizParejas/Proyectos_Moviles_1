package com.example.aplicacionchofer.network

import com.example.aplicacionchofer.model.Location
import com.example.aplicacionchofer.model.LoginData
import com.example.aplicacionchofer.model.OrderUpdate
import com.example.aplicacionchofer.model.Pedido
import com.example.aplicacionchofer.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(@Body loginData: LoginData): Call<Usuario>

    @POST("register")
    fun register(@Body usuario: Usuario): Call<Usuario>

    @GET("orders")
    fun getOrders(): Call<List<Pedido>>

    @POST("orders/accept")
    fun acceptOrder(@Body orderId: Int): Call<Void>

    @POST("orders/update")
    fun updateOrderStatus(@Body orderUpdate: OrderUpdate): Call<Void>

    @POST("location/update")
    fun updateLocation(@Body location: Location): Call<Void>
}

package com.example.aplicacincliente.api

import com.example.aplicacioncliente.api.MenuItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {
    @GET("restaurants")
    fun getRestaurants(): Call<List<Restaurante>>

    @GET("restaurants/{id}/menu")
    fun getRestaurantMenu(@Path("id") id: String): Call<List<MenuItem>>
}

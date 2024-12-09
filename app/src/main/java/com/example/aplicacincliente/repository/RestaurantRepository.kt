package com.example.aplicacincliente.repository

import com.example.aplicacincliente.api.ApiClient
import com.example.aplicacincliente.api.RestaurantApi

class RestaurantRepository {
    private val restaurantApi = ApiClient.retrofit.create(RestaurantApi::class.java)

    fun getRestaurants() = restaurantApi.getRestaurants()
}

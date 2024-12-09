package com.example.aplicacioncliente.repository

import com.example.aplicacioncliente.api.ProductoApi
import com.example.aplicacincliente.api.ApiClient

class ProductRepository {
    private val productApi = ApiClient.retrofit.create(ProductoApi::class.java)

    suspend fun getProducts(token: String) = productApi.getProductos(token)
}

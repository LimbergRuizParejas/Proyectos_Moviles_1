package com.example.aplicacioncliente.network

import com.example.aplicacionchofer.model.*
import com.example.aplicacionchofer.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    fun login(loginData: LoginData, onResult: (Usuario?) -> Unit) {
        apiService.login(loginData).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun register(usuario: Usuario, onResult: (Usuario?) -> Unit) {
        apiService.register(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun getOrders(onResult: (List<Pedido>?) -> Unit) {
        apiService.getOrders().enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun acceptOrder(orderId: Int, onResult: (Boolean) -> Unit) {
        apiService.acceptOrder(orderId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun updateOrderStatus(orderUpdate: OrderUpdate, onResult: (Boolean) -> Unit) {
        apiService.updateOrderStatus(orderUpdate).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun updateLocation(location: Location, onResult: (Boolean) -> Unit) {
        apiService.updateLocation(location).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onResult(false)
            }
        })
    }
}

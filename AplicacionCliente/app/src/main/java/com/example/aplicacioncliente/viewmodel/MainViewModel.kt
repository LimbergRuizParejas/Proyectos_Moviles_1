package com.example.aplicacionchofer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplicacionchofer.model.*
import com.example.aplicacioncliente.network.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> get() = _usuario

    private val _pedidos = MutableLiveData<List<Pedido>?>()
    val pedidos: LiveData<List<Pedido>?> get() = _pedidos

    private val _orderStatus = MutableLiveData<Boolean>()
    val orderStatus: LiveData<Boolean> get() = _orderStatus

    private val _locationStatus = MutableLiveData<Boolean>()
    val locationStatus: LiveData<Boolean> get() = _locationStatus

    fun login(loginData: LoginData) {
        repository.login(loginData) {
            _usuario.value = it
        }
    }

    fun register(usuario: Usuario) {
        repository.register(usuario) {
            _usuario.value = it
        }
    }

    fun getOrders() {
        repository.getOrders {
            _pedidos.value = it
        }
    }

    fun acceptOrder(orderId: Int) {
        repository.acceptOrder(orderId) {
            _orderStatus.value = it
        }
    }

    fun updateOrderStatus(orderUpdate: OrderUpdate) {
        repository.updateOrderStatus(orderUpdate) {
            _orderStatus.value = it
        }
    }

    fun updateLocation(location: Location) {
        repository.updateLocation(location) {
            _locationStatus.value = it
        }
    }
}

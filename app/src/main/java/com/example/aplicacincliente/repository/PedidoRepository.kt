package com.example.aplicacincliente.repository

import com.example.aplicacioncliente.api.PedidoApi
import com.example.aplicacincliente.api.Pedido
import okhttp3.RequestBody

class PedidoRepository(private val pedidoApi: PedidoApi) {
    suspend fun crearPedido(pedido: Pedido, token: String) = pedidoApi.crearPedido(pedido, token)

    suspend fun getPedidos(token: String) = pedidoApi.getPedidos(token)

    suspend fun getPedidosSinAsignar(token: String) = pedidoApi.getPedidosSinAsignar(token)

    suspend fun getPedido(id: Int, token: String) = pedidoApi.getPedido(id, token)

    suspend fun aceptarPedido(id: Int, token: String) = pedidoApi.aceptarPedido(id, token)

    suspend fun pedidoEnCamino(id: Int, token: String) = pedidoApi.pedidoEnCamino(id, token)

    suspend fun pedidoEntregado(id: Int, token: String) = pedidoApi.pedidoEntregado(id, token)

    suspend fun pruebaEntrega(id: Int, foto: RequestBody, token: String) = pedidoApi.pruebaEntrega(id, foto, token)
}

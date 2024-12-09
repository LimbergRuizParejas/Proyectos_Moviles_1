package com.example.aplicacincliente.repository

import android.location.LocationRequest
import com.example.aplicacincliente.api.ChoferApi

class ChoferRepository(private val choferApi: ChoferApi) {
    suspend fun actualizarUbicacion(ubicacion: LocationRequest, token: String) = choferApi.actualizarUbicacion(ubicacion, token)
}

package com.example.aplicacincliente.api

import android.location.LocationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChoferApi {
    @POST("drivers/location")
    suspend fun actualizarUbicacion(@Body ubicacion: LocationRequest, @Header("Authorization") token: String): Response<Unit>
}

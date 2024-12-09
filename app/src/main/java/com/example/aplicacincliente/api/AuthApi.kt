package com.example.aplicacioncliente.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("users/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}

package com.example.aplicacioncliente.repository

import com.example.aplicacioncliente.api.AuthApi
import com.example.aplicacioncliente.api.LoginRequest
import com.example.aplicacioncliente.api.LoginResponse
import com.example.aplicacioncliente.api.RegisterRequest
import com.example.aplicacioncliente.api.RegisterResponse
import retrofit2.Response

class UserRepository(private val api: AuthApi) {

    suspend fun register(email: String, password: String, name: String, role: Int): Response<RegisterResponse> {
        val registerRequest = RegisterRequest(email, password, name, role)
        return api.register(registerRequest)
    }

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return api.login(loginRequest)
    }
}

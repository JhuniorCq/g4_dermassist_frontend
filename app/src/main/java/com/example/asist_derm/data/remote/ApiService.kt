package com.example.asist_derm.data.remote

import com.example.asist_derm.data.model.RegisterRequest
import com.example.asist_derm.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>
}
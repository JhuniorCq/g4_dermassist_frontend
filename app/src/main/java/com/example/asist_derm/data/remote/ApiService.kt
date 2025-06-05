package com.example.asist_derm.data.remote

import com.example.asist_derm.data.model.RegisterRequest
import com.example.asist_derm.data.model.RegisterResponse
import com.example.asist_derm.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("user")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("user/{uid}")
    suspend fun getUserByUID(@Path("uid") uid: String): Response<UserResponse>
}

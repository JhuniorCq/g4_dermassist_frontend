package com.example.asist_derm.data.remote

import com.example.asist_derm.data.model.DiseaseRequest
import com.example.asist_derm.data.model.DiseaseResponse
import com.example.asist_derm.data.model.HistoryResponse
import com.example.asist_derm.data.model.PredictionResponse
import com.example.asist_derm.data.model.RegisterRequest
import com.example.asist_derm.data.model.RegisterResponse
import com.example.asist_derm.data.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @Multipart
    @POST("prediction")
    suspend fun makePrediction(
        @Part image: MultipartBody.Part,
        @Part("uid") uid: RequestBody
    ): Response<PredictionResponse>


    @POST("user")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("user/{uid}")
    suspend fun getUserByUID(@Path("uid") uid: String): Response<UserResponse>

    @POST("disease/info")
    suspend fun getInfo(@Body request: DiseaseRequest): DiseaseResponse

    @GET("prediction/{uid}")
    suspend fun getPredictionHistory(@Path("uid") uid: String): Response<HistoryResponse>


}

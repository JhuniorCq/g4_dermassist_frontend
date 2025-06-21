package com.example.asist_derm.data.repository

import android.util.Log
import com.example.asist_derm.data.model.Prediction
import com.example.asist_derm.data.remote.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PredictionRepository {
    suspend fun predictDisease(uid: String, imageFile: File): Prediction? {
        val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
        val uidPart = uid.toRequestBody("text/plain".toMediaTypeOrNull())

        val response = RetrofitInstance.api.makePrediction(imagePart, uidPart)
        return if (response.isSuccessful && response.body()?.success == true) {
            response.body()?.data
        } else {
            Log.e("PredictionRepo", "Error: ${response.errorBody()?.string()}")
            null
        }
    }
}
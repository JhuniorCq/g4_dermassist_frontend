package com.example.asist_derm.data.repository

import android.util.Log
import com.example.asist_derm.data.model.PredictionHistoryItem
import com.example.asist_derm.data.remote.RetrofitInstance

class HistoryRepository {
    suspend fun getPredictionHistory(uid: String): List<PredictionHistoryItem>? {
        return try {
            val response = RetrofitInstance.api.getPredictionHistory(uid)
            if (response.isSuccessful) {
                Log.d("HISTORY", "Respuesta: ${response.body()}")
                response.body()?.data
            } else {
                Log.e("HISTORY", "Error: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}



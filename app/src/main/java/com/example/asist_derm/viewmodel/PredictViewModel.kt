package com.example.asist_derm.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asist_derm.data.model.Prediction
import com.example.asist_derm.data.repository.PredictionRepository
import kotlinx.coroutines.launch
import java.io.File
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.asist_derm.data.model.DiseaseRequest
import com.example.asist_derm.data.remote.ApiService
import com.example.asist_derm.data.remote.RetrofitInstance

class PredictViewModel : ViewModel() {
    private val repository = PredictionRepository()

    var prediction by mutableStateOf<Prediction?>(null)
    var isLoading by mutableStateOf(false)
    var diseaseDetail by mutableStateOf<String?>(null)
        private set

    fun analyzeImage(uid: String, imageFile: File) {
        viewModelScope.launch {
            isLoading = true
            prediction = repository.predictDisease(uid, imageFile)
            isLoading = false
        }
    }
    fun getDiseaseInfo(disease: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                Log.d("PredictViewModel", "Solicitando info para: $disease")
                val response = RetrofitInstance.api.getInfo(DiseaseRequest(disease))
                Log.d("PredictViewModel", "Respuesta recibida: ${response.data.response}")
                diseaseDetail = response.data.response
            } catch (e: Exception) {
                Log.e("PredictViewModel", "Error obteniendo detalle: ${e.message}")
                diseaseDetail = "No se pudo obtener el detalle de la enfermedad."
            } finally {
                isLoading = false
            }
        }
    }

}

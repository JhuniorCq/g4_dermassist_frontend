package com.example.asist_derm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asist_derm.data.model.Prediction
import com.example.asist_derm.data.repository.PredictionRepository
import kotlinx.coroutines.launch
import java.io.File
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PredictViewModel : ViewModel() {
    private val repository = PredictionRepository()

    var prediction by mutableStateOf<Prediction?>(null)
    var isLoading by mutableStateOf(false)

    fun analyzeImage(uid: String, imageFile: File) {
        viewModelScope.launch {
            isLoading = true
            prediction = repository.predictDisease(uid, imageFile)
            isLoading = false
        }
    }
}

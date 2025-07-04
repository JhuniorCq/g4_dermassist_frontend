package com.example.asist_derm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asist_derm.data.model.PredictionHistoryItem
import com.example.asist_derm.data.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val repository = HistoryRepository()

    var predictions by mutableStateOf<List<PredictionHistoryItem>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    fun fetchHistory(uid: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.getPredictionHistory(uid)
            if (result != null) {
                predictions = result
            }
            isLoading = false
        }
    }
}
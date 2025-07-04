package com.example.asist_derm.data.model

data class HistoryResponse(
    val success: Boolean,
    val message: String,
    val data: List<PredictionHistoryItem>
)

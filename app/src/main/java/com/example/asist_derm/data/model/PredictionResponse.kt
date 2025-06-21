package com.example.asist_derm.data.model

data class PredictionResponse(
    val success: Boolean,
    val message: String,
    val data: Prediction
)

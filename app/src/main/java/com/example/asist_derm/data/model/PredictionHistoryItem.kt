package com.example.asist_derm.data.model

data class PredictionHistoryItem(
    val prediction_id: Int,
    val name: String,
    val probability: Double,
    val image_url: String,
    val created_at: String,
    val user_id: String
)


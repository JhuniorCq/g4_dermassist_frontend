package com.example.asist_derm.data.model

data class UserResponse (
        val success: Boolean,
        val message: String,
        val data: UserData
    )
    data class UserData(
        val uid: String,
        val username: String,
        val email: String
    )

package com.appgenesis.com.maintenanceapp.model

data class ForgotPasswordRequest(
    val email: String,
    val password: String? = null,
    val password_confirmation: String? = null,
    val token: String? = null
)
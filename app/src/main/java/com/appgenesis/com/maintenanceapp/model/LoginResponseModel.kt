package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user")
    val user: User
)
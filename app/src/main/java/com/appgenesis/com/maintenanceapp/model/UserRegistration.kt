package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class UserRegistration(
    @SerializedName("password")
    val password: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_first_name")
    val userFirstName: String,
    @SerializedName("user_last_name")
    val userLastName: String,
    @SerializedName("user_phone")
    val userPhone: String,
    @SerializedName("user_role")
    val userRole: String
)
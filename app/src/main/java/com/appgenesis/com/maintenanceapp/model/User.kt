package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_first_name")
    val userFirstName: String,
    @SerializedName("user_full_name")
    val userFullName: String,
    @SerializedName("user_last_name")
    val userLastName: String,
    @SerializedName("user_phone")
    val userPhone: String,
    @SerializedName("user_role")
    val userRole: String,
    @SerializedName("user_status")
    val userStatus: String
)
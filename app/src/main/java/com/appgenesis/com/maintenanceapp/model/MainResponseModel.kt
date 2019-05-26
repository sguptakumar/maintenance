package com.appgenesis.com.maintenanceapp.model
import com.google.gson.annotations.SerializedName


data class MainResponseModel(
    @SerializedName("message")
    val message: String
)
package com.appgenesis.com.maintenanceapp.model

import com.google.gson.annotations.SerializedName

data class MaintenanceListResponse(
    @SerializedName("maintenanceRequests")
    val maintenanceRequests: List<MaintenanceRequest>)
